package br.com.fbueno.libraryAPI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryApiApplication

fun main(args: Array<String>) {
	runApplication<LibraryApiApplication>(*args)
}
