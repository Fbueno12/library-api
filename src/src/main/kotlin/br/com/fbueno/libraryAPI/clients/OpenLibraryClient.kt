package br.com.fbueno.libraryAPI.clients

import br.com.fbueno.libraryAPI.models.dto.openLibrary.OpenLibraryResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "openLibrary", url = "https://openlibrary.org/")
interface OpenLibraryClient {
    @GetMapping("/search.json?title={title}")
    fun searchBooks(@PathVariable title: String): OpenLibraryResponse

}