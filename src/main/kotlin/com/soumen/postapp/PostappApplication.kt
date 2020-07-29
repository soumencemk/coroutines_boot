package com.soumen.postapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.logging.Logger

@SpringBootApplication
class PostappApplication

fun main(args: Array<String>) {
    runApplication<PostappApplication>(*args)
}

/*Logger config*/
fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { Logger.getLogger((this.javaClass).name) }
}
