package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;

abstract public class EventSelectors implements EventSelector {

    public static EventSelectors where(EventSelector selector) {
        return new WhereEventSelector(selector);
    }

    public static EventSelectors not(final EventSelector selector) {
        return new NotEventSelector(selector);
    }

    public EventSelectors and(final EventSelector eventSelector) {
        return new AndEventSelector(this, eventSelector);
    }

    public EventSelectors or(final EventSelector eventSelector) {
        return new OrEventSelector(this, eventSelector);
    }

    public static class NotEventSelector extends EventSelectors {

        final private EventSelector selector;

        public NotEventSelector(EventSelector selector) {
            this.selector = selector;
        }

        @Override
        public boolean filter(Event event) {
            return !selector.filter(event);
        }

    }

    public static class AndEventSelector extends EventSelectors {

        final private EventSelector selector;
        final private EventSelector eventSelector;

        public AndEventSelector(EventSelector selector, EventSelector eventSelector) {
            this.selector = selector;
            this.eventSelector = eventSelector;
        }

        @Override
        public boolean filter(Event event) {
            return selector.filter(event) && eventSelector.filter(event);
        }

    }

    public static class WhereEventSelector extends EventSelectors {

        final private EventSelector selector;

        public WhereEventSelector(EventSelector selector) {
            this.selector = selector;
        }

        @Override
        public boolean filter(Event event) {
            return selector != null ? selector.filter(event) : true;
        }

    }

    public static class OrEventSelector extends EventSelectors {

        final private EventSelector selector;
        final private EventSelector eventSelector;

        public OrEventSelector(EventSelector selector, EventSelector eventSelector) {
            this.selector = selector;
            this.eventSelector = eventSelector;
        }

        @Override
        public boolean filter(Event event) {
            return selector.filter(event) || eventSelector.filter(event);
        }

    }

}
