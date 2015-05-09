package com.clemble.casino.client.event;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

import com.clemble.casino.ImmutablePair;
import com.clemble.casino.client.payment.PaymentEventSelector;
import com.clemble.casino.event.Event;
import com.clemble.casino.payment.event.PaymentEvent;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

abstract public class AbstractEventListenerTemplate implements EventListenerOperations, Closeable {

    /**
     * Generated 29/11/13
     */
    private static final long serialVersionUID = 6661393998011634474L;

    final private String player;

    final private NotificationThread notificationService;

    final protected ScheduledExecutorService executor;
    final protected AtomicReference<Closeable> connectionCleaner = new AtomicReference<Closeable>();

    public AbstractEventListenerTemplate(String player) {
        this.player = checkNotNull(player);
        this.notificationService = new NotificationThread();
        this.executor = Executors.newScheduledThreadPool(2, new ThreadFactoryBuilder().setNameFormat(player + "_listener_%d").build());
        this.executor.execute(notificationService);
    }

    @Override
    final public String getPlayer() {
        return player;
    }

    @Override
    final public EventListenerController subscribe(EventListener<Event> listener) {
        if (listener instanceof EventSelector) {
            return subscribe((EventSelector) listener, listener);
        } else {
            return subscribe((EventSelector) null, listener);
        }
    }

    @Override
    final public EventListenerController subscribe(EventSelector selector, EventListener<? extends Event> listener) {
        return subscribe(player, selector, listener);
    }

    @Override
    final public EventListenerController subscribe(String channel, EventListener<? extends Event> listener) {
        return subscribe(channel, null, listener);
    }

    @Override
    final public EventListenerController subscribe(String channel, EventSelector selector, EventListener<? extends Event> listener) {
        return notificationService.subscribe(channel, selector, listener);
    }

    protected void refreshSubscription() {
        for (String channel : notificationService.getChannels())
            subscribe(channel);
    }

    @Override
    final public EventListenerController subscribeToPaymentEvents(EventListener<PaymentEvent> listener) {
        return subscribe(new PaymentEventSelector(), listener);
    }

    abstract public void subscribe(String channel);

    final public void update(Collection<? extends Event> events) {
        // Step 1. Sanity check
        if (events == null)
            return;
        // Step 2. Notifying event after event
        for (Event event : events)
            update(event);
    }

    final public void update(Event event) {
        // Step 1. Checking event value
        if (event == null)
            return;
        // Step 2. Emulating server event
        update(player, event);
    }

    protected void update(String channel, Event event) {
        // Step 1. Checking event value
        if (event == null)
            return;
        // Step 2. Checking and notifying Event selectors
        notificationService.publish(channel, event);
    }

    public void close() {
        try {
            if (connectionCleaner.get() != null) {
                try {
                    connectionCleaner.get().close();
                } catch (IOException e) {
                }
            }
        } finally {
            // Simple shutdown won't work, this would leave notifier hanging
            executor.shutdownNow();
        }
    }

    private class NotificationThread implements Runnable {

        final private Map<String, List<Entry<EventSelector, EventListener<?>>>> channelToListeners = new HashMap<String, List<Entry<EventSelector, EventListener<?>>>>();
        final private BlockingQueue<Entry<String, Event>> events = new LinkedBlockingQueue<Entry<String, Event>>();

        public Collection<String> getChannels() {
            return channelToListeners.keySet();
        }

        public EventListenerController subscribe(String channel, EventSelector selector, EventListener<?> listener) {
            if (!channelToListeners.containsKey(channel)) {
                channelToListeners.put(channel, new CopyOnWriteArrayList<Entry<EventSelector, EventListener<?>>>());
                AbstractEventListenerTemplate.this.subscribe(channel);
            }
            // Step 1. Generating event listener to use
            final ImmutablePair<EventSelector, EventListener<?>> listenerPair = new ImmutablePair<EventSelector, EventListener<?>>(selector, listener);
            // Step 2. Adding eventListener to the list
            this.channelToListeners.get(channel).add(listenerPair);
            // Step 3. Adding eventListener controller
            return new EventListenerController() {
                @Override
                public void close() {
                    channelToListeners.remove(listenerPair);
                }
            };
        }

        public void publish(String channel, Event event) {
            events.add(new ImmutablePair<String, Event>(channel, event));
        }

        @Override
        @SuppressWarnings("unchecked")
        public void run() {
            while (true && !executor.isShutdown()) {
                try {
                    Entry<String, Event> notification = events.take();
                    String channel = notification.getKey();
                    Event event = notification.getValue();
                    for (Entry<EventSelector, EventListener<?>> listener : channelToListeners.get(channel)) {
                        try {
                            EventSelector selector = listener.getKey();
                            if (selector == null || selector.filter(event))
                                ((EventListener<Event>) listener.getValue()).onEvent(event);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                } catch (InterruptedException interruptedException) {
                    return;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
