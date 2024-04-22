package br.com.fbueno.libraryAPI.factory

import br.com.fbueno.libraryAPI.models.dto.openLibrary.DocsDTO
import br.com.fbueno.libraryAPI.models.dto.openLibrary.OpenLibraryResponse
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class BookModelFactoryTest {

    private val docsTitle = "bookTitle"
    private val docsdescription = "bookDescription"
    private val docsauthor = listOf("bookAuthor")

    @InjectMocks
    lateinit var bookModelFactory: BookModelFactory

    @Mock
    lateinit var openLibResponse: OpenLibraryResponse

    @Mock
    lateinit var docsDTO: DocsDTO

    @Test
    fun `createInstance, should create a bookModel`() {
        whenever(docsDTO.title).thenReturn(docsTitle)
        whenever(docsDTO.subtitle).thenReturn(docsdescription)
        whenever(docsDTO.authorName).thenReturn(docsauthor)
        whenever(openLibResponse.docs).thenReturn(listOf(docsDTO))
        val result = bookModelFactory.createInstance(openLibResponse)
        assertNotNull(result)
        assertEquals(docsTitle, result.title)
        assertEquals(docsdescription, result.description)
        assertEquals(docsauthor.first(), result.author)
    }

    @Test
    fun `getObjectType, should return a BookModel class`() {
        val result = bookModelFactory.objectType
        assertInstanceOf(Class::class.java, result)
    }
}