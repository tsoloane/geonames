package dev.tsoloane.medium.geonames

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class GeonamesApplication

fun main(args: Array<String>) {
    runApplication<GeonamesApplication>(*args)
}
