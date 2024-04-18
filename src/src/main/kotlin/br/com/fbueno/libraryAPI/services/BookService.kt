package br.com.fbueno.libraryAPI.services

import br.com.fbueno.libraryAPI.exceptions.BookAlreadyExistsException
import br.com.fbueno.libraryAPI.exceptions.BookNotFoundException
import br.com.fbueno.libraryAPI.exceptions.BookTitleAlreadyExistsException
import br.com.fbueno.libraryAPI.extensions.toModel
import br.com.fbueno.libraryAPI.models.BookModel
import br.com.fbueno.libraryAPI.models.dto.BookDTO
import br.com.fbueno.libraryAPI.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun index(): List<BookModel> {
        return bookRepository.findAll()
    }

    fun find(id: Long): BookModel {
        return bookRepository.findById(id).orElseThrow { throw BookNotFoundException() }
    }

    fun save(book: BookDTO): BookModel {
        if (bookRepository.findByTitle(book.title) != null)
            throw BookAlreadyExistsException()

        return bookRepository.save(book.toModel())
    }

    fun update(id: Long, book: BookDTO) {
        val updatedBook = bookRepository.findById(id).orElseThrow { throw BookNotFoundException() }
        if (bookRepository.findByTitle(book.title) != null) throw BookTitleAlreadyExistsException()
        updatedBook.apply {
            title = book.title
            description = book.description
            author = book.author
        }
        bookRepository.save(updatedBook)
    }
}