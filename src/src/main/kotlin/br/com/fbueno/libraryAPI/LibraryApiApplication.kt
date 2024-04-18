package br.com.fbueno.libraryAPI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class LibraryApiApplication

fun main(args: Array<String>) {
	runApplication<LibraryApiApplication>(*args)
}
