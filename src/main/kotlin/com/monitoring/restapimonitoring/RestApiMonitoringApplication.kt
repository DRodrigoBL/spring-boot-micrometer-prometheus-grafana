package com.monitoring.restapimonitoring

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import java.util.concurrent.TimeUnit

@SpringBootApplication
class RestApiMonitoringApplication(val dummyService: DummyService) {

    @EventListener(ApplicationReadyEvent::class)
    fun switchStores() {
        Observable.interval(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.trampoline())
            .subscribe {
                dummyService.toggleStore(it.toInt())
            }
    }
}

fun main(args: Array<String>) {
    runApplication<RestApiMonitoringApplication>(*args)
}
