package br.com.fbueno.libraryAPI.services

import br.com.fbueno.libraryAPI.clients.OpenLibraryClient
import br.com.fbueno.libraryAPI.exceptions.BookAlreadyExistsException
import br.com.fbueno.libraryAPI.exceptions.BookNotFoundException
import br.com.fbueno.libraryAPI.factory.BookModelFactory
import br.com.fbueno.libraryAPI.models.BookModel
import br.com.fbueno.libraryAPI.models.dto.BookDTO
import br.com.fbueno.libraryAPI.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository,
    val openLibraryClient: OpenLibraryClient,
    val bookModelFactory: BookModelFactory
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

        val openLibBook = openLibraryClient.searchBooks(book.title) // fazer tratamento de erros caso a api dê erro.
        val generatedBook = bookModelFactory.createInstance(openLibBook)

        return bookRepository.save(generatedBook)
    }

    fun update(id: Long, book: BookDTO) {
        val updatedBook = this.find(id)
        updatedBook.apply {
            title = book.title
            description = book.description!!
            author = book.author!!
        }
        bookRepository.save(updatedBook)
    }

    fun delete(id: Long) {
        this.find(id)
        bookRepository.deleteById(id)
    }
}