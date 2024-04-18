package br.com.fbueno.libraryAPI.repository

import br.com.fbueno.libraryAPI.models.BookModel
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookModel, Long> {
    fun findByTitle(title: String): BookModel?
}