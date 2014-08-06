package com.clemble.casino.client.event;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.clemble.casino.ImmutablePair;
import com.clemble.casino.client.payment.PaymentEventSelector;
import com.clemble.casino.event.Event;
import com.clemble.casino.event.NotificationMapping;
import com.clemble.casino.payment.event.PaymentEvent;
import com.clemble.casino.player.PlayerPresenceChangedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.Queue;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

public class RabbitEventListenerTemplate extends AbstractEventListenerTemplate {

    /**
     * Generated 29/11/13
     */
    private static final long serialVersionUID = 5524090397307090488L;

    final private AtomicReference<Entry<Channel, Queue.DeclareOk>> rabbitQueue = new AtomicReference<Entry<Channel, Queue.DeclareOk>>();

    public RabbitEventListenerTemplate(String player, String host, ObjectMapper objectMapper) {
        super(player);
        this.executor.submit(new StartupTask(host.substring(0, host.indexOf(":") > 0 ? host.indexOf(":") : host.length()), objectMapper));
    }

    final public class StartupTask implements Runnable {

        final public String host;
        final public ObjectMapper objectMapper;

        public StartupTask(String configuration, ObjectMapper objectMapper) {
            this.host = checkNotNull(configuration);
            this.objectMapper = checkNotNull(objectMapper);
        }

        @Override
        public void run() {
            try {
                synchronized (rabbitQueue) {
                    // Step 1. Generalizing connection factory
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setUsername("guest");
                    factory.setPassword("guest");
                    factory.setHost(host);
                    factory.setPort(5672);
                    // Step 2. Creating connection
                    final Connection rabbitConnection = factory.newConnection(executor);
                    final AtomicBoolean keepClosed = new AtomicBoolean(false);
                    // Step 3. Creating new Channel
                    Channel channel = rabbitConnection.createChannel();
                    channel.addShutdownListener(new ShutdownListener() {
                        @Override
                        public void shutdownCompleted(ShutdownSignalException cause) {
                            if (!keepClosed.get()) {
                                cause.printStackTrace();
                                executor.schedule(StartupTask.this, 30, TimeUnit.SECONDS);
                            }
                        }
                    });
                    // Step 4. Creating new Queue that will be used for listening of player updates
                    Queue.DeclareOk queue = channel.queueDeclare();
                    channel.basicConsume(queue.getQueue(), new AndroidRabbitDefaultConsumer(channel, objectMapper));
                    rabbitQueue.set(new ImmutablePair<Channel, Queue.DeclareOk>(channel, queue));
                    // Step 5. Updating Closeable in the parent
                    connectionCleaner.set(new Closeable() {
                        @Override
                        public void close() throws IOException {
                            keepClosed.set(true);
                            rabbitConnection.close();
                        }
                    });
                    refreshSubscription();
                }
            } catch (Throwable e) {
                e.printStackTrace();
                executor.schedule(this, 30, TimeUnit.SECONDS);
            }
        }

    }

    final public class AndroidRabbitDefaultConsumer extends DefaultConsumer {

        final private ObjectMapper objectMapper;

        public AndroidRabbitDefaultConsumer(Channel channel, ObjectMapper objectMapper) {
            super(channel);
            this.objectMapper = objectMapper;
        }

        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            try {
                update(envelope.getRoutingKey(), objectMapper.readValue(body, Event.class));
            } catch (Throwable throwable) {
                System.out.println("Failed to parse " + new String(body));
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public void subscribe(String routingKey) {
        if (isAlive()) {
            Channel channel = rabbitQueue.get().getKey();
            Queue.DeclareOk queue = rabbitQueue.get().getValue();
            try {
                channel.queueBind(queue.getQueue(), "amq.topic", routingKey);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean isAlive() {
        return rabbitQueue.get() != null;
    }

}
