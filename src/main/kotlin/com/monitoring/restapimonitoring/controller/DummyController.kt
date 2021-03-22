package com.monitoring.restapimonitoring.controller

import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DummyController(val meterRegistry: MeterRegistry) {

    private val logger by lazy { LoggerFactory.getLogger(DummyController::class.java.simpleName) }

    @GetMapping("/api/rest/{value}")
    fun useValue(@PathVariable("value") value: Int): String {
        logger.info("value to use: $value")
        return value.toString()
    }
}
