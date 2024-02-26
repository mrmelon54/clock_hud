package com.mrmelon54.ClockHud.old;

#if MC_VER == MC_1_16_5
import me.shedaniel.architectury.event.Event;
import me.shedaniel.architectury.event.EventFactory;

import java.util.function.Function;

public class EventWrapper<N> implements Event<N> {
    private final Event<N> outerEvent = EventFactory.createLoop();

    private EventWrapper() {
    }

    public static <N, O> EventWrapper<N> create(Event<O> innerEvent, Function<N, O> converter) {
        EventWrapper<N> wrapper = new EventWrapper<>();
        innerEvent.register(converter.apply(wrapper.outerEvent.invoker()));
        return wrapper;
    }

    @Override
    public N invoker() {
        return outerEvent.invoker();
    }

    @Override
    public void register(N listener) {
        outerEvent.register(listener);
    }

    @Override
    public void unregister(N listener) {
        outerEvent.unregister(listener);
    }

    @Override
    public boolean isRegistered(N listener) {
        return outerEvent.isRegistered(listener);
    }

    @Override
    public void clearListeners() {
        outerEvent.clearListeners();
    }
}
#endif
