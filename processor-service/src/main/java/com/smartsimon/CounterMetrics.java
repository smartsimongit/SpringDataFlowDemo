package com.smartsimon;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Component;

@Component
public class CounterMetrics {

    private MeterRegistry meterRegistry;
    private  Counter counter;

    public CounterMetrics(final MeterRegistry meterRegistry) {
        System.out.println("ACHTUNG! 1");
        this.meterRegistry = meterRegistry;
        System.out.println("ACHTUNG! 2");
        initCounter();

    }

    private void initCounter() {
        System.out.println("ACHTUNG! 3");
        counter = Counter.builder("bad-words.find")    // 2 - create a counter using the fluent API
                .tag("type", "word")
                .description("How much bad words we find")
                .register(meterRegistry);

    }

    public void incrementCount() {
        System.out.println("ACHTUNG! INCREMENT");
        counter.increment();
    }

}
