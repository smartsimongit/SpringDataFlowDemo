package com.smartsimon;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CounterMetrics {
    Logger logger = LoggerFactory.getLogger(ProcessorServiceApplication.class);
    private MeterRegistry meterRegistry;
    private Counter wordCounter;
    private Counter fileCounter;

    public CounterMetrics(final MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initCounter();
        logger.info("\nCounterMetrics created!");
    }

    private void initCounter() {
        wordCounter = Counter.builder("bad-words.find")
                .tag("type", "word")
                .description("How much bad words we find")
                .register(meterRegistry);
        fileCounter = Counter.builder("file.find")
                .tag("type", "file")
                .description("How much file finded")
                .register(meterRegistry);

    }

    public void incrementWordCount() {
        wordCounter.increment();
    }

    public void incrementFileCount() {
        fileCounter.increment();
    }

}
