package com.monitoring.restapimonitoring

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DummyService(val meterRegistry: MeterRegistry) {

    private lateinit var toggleStoreOnCounter: Counter
    private lateinit var toggleStoreOffCounter: Counter

    private val logger by lazy { LoggerFactory.getLogger(DummyService::class.java.simpleName) }

    @PostConstruct
    fun initCounters() {
        toggleStoreOnCounter = Counter.builder("toggle.store")
            .tag("toggle-type", "on")
            .description("Turn on store")
            .register(meterRegistry)

        toggleStoreOffCounter = Counter.builder("toggle.store")
            .tag("toggle-type", "off")
            .description("Turn off store")
            .register(meterRegistry)
    }

    fun toggleStore(value: Int) {
        if (value % 2 == 0) {
            logger.info("Turn on store")
            toggleStoreOnCounter.increment()
        } else {
            logger.info("Turn off store")
            toggleStoreOffCounter.increment()
        }
    }
}
