package br.com.fbueno.libraryAPI.services

import br.com.fbueno.libraryAPI.clients.OpenLibraryClient
import br.com.fbueno.libraryAPI.exceptions.BookAlreadyExistsException
import br.com.fbueno.libraryAPI.exceptions.BookNotFoundException
import br.com.fbueno.libraryAPI.factory.BookModelFactory
import br.com.fbueno.libraryAPI.models.BookModel
import br.com.fbueno.libraryAPI.models.dto.BookDTO
import br.com.fbueno.libraryAPI.models.dto.openLibrary.OpenLibraryResponse
import br.com.fbueno.libraryAPI.repository.BookRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import java.util.*

@ExtendWith(MockitoExtension::class)
class BookServiceTest {

    private val bookAuthor = "book author"
    private val bookDescription = "book description"
    private val id = 1L
    private val bookTitle = "bootTitle"

    @Mock
    lateinit var bookRepository: BookRepository

    @Mock
    lateinit var openLibraryClient: OpenLibraryClient

    @Mock
    lateinit var bookModelFactory: BookModelFactory

    @InjectMocks
    lateinit var bookService: BookService

    @Mock
    lateinit var book: BookModel

    @Mock
    lateinit var bookDTO: BookDTO

    @Mock
    lateinit var openLibResponse: OpenLibraryResponse

    @Test
    fun `index, should list of books`() {
        whenever(bookRepository.findAll()).thenReturn(listOf(book))
        val result = bookService.index()
        assertNotNull(result)
        assertEquals(listOf(book), result)
    }

    @Test
    fun `find, should find a book`() {
        whenever(bookRepository.findById(id)).thenReturn(Optional.of(book))
        val result = bookService.find(id)
        assertNotNull(result)
        assertEquals(book, result)
    }

    @Test
    fun `find, should not find a book`() {
        whenever(bookRepository.findById(id)).thenReturn(Optional.empty())
        assertThrows(BookNotFoundException::class.java) {
            bookService.find(id)
        }
    }

    @Test
    fun `save, should save a book`() {
        whenever(bookDTO.title).thenReturn(bookTitle)
        whenever(bookRepository.findByTitle(bookTitle)).thenReturn(null)
        whenever(openLibraryClient.searchBooks(bookTitle)).thenReturn(openLibResponse)
        whenever(bookModelFactory.createInstance(openLibResponse)).thenReturn(book)
        whenever(bookRepository.save(book)).thenReturn(book)
        val result = bookService.save(bookDTO)
        assertNotNull(result)
        assertEquals(book, result)
    }

    @Test
    fun `save, should not save duplicated book`() {
        whenever(bookDTO.title).thenReturn(bookTitle)
        whenever(bookRepository.findByTitle(bookTitle)).thenReturn(book)
        assertThrows(BookAlreadyExistsException::class.java) {
            bookService.save(bookDTO)
        }
    }

    @Test
    fun `update, should update a book`() {
        whenever(bookRepository.findById(id)).thenReturn(Optional.of(book))
        whenever(bookDTO.title).thenReturn(bookTitle)
        whenever(bookDTO.description).thenReturn(bookDescription)
        whenever(bookDTO.author).thenReturn(bookAuthor)
        val result = bookService.update(id, bookDTO)
        verify(bookRepository, times(1)).save(book)
    }

    @Test
    fun `update, should not update a book the wasn't found`() {
        whenever(bookRepository.findById(id)).thenReturn(Optional.empty())
        assertThrows(BookNotFoundException::class.java) {
            bookService.update(id, bookDTO)
        }
    }

    @Test
    fun `delete, should delete a book`() {
        whenever(bookRepository.findById(id)).thenReturn(Optional.of(book))
        val result = bookService.delete(id)
        verify(bookRepository, times(1)).deleteById(id)
    }

    @Test
    fun `delete, should delete a unknown book`() {
        whenever(bookRepository.findById(id)).thenReturn(Optional.empty())
        assertThrows(BookNotFoundException::class.java) {
            bookService.delete(id)
        }
    }
}