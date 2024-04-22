package br.com.fbueno.libraryAPI.controllers

import br.com.fbueno.libraryAPI.models.BookModel
import br.com.fbueno.libraryAPI.models.dto.BookDTO
import br.com.fbueno.libraryAPI.models.dto.DefaultResponseDTO
import br.com.fbueno.libraryAPI.services.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(
    val bookService: BookService
) {
    @GetMapping
    fun index(): ResponseEntity<DefaultResponseDTO<List<BookModel>>> {
        return ResponseEntity.ok(DefaultResponseDTO(bookService.index()))
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Long): ResponseEntity<DefaultResponseDTO<BookModel>> {
        return ResponseEntity.ok(DefaultResponseDTO(bookService.find(id)))
    }

    @PostMapping
    fun save(@RequestBody book: BookDTO): ResponseEntity<DefaultResponseDTO<BookModel>> {
        return ResponseEntity.ok(DefaultResponseDTO(bookService.save(book)))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(
        @PathVariable id: Long,
        @RequestBody book: BookDTO
    ) {
        bookService.update(id, book)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        bookService.delete(id)
    }
}