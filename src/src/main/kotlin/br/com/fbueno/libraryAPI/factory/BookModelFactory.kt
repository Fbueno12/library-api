package br.com.fbueno.libraryAPI.factory

import br.com.fbueno.libraryAPI.models.BookModel
import br.com.fbueno.libraryAPI.models.dto.openLibrary.OpenLibraryResponse
import org.springframework.beans.factory.config.AbstractFactoryBean
import org.springframework.stereotype.Component

@Component
class BookModelFactory : AbstractFactoryBean<BookModel>() {
    override fun getObjectType(): Class<*> {
        return BookModel::class.java
    }

    override fun createInstance(): BookModel {
        return BookModel(null, "", "", "")
    }

    fun createInstance(openLibraryResponse: OpenLibraryResponse): BookModel {
        val book = openLibraryResponse.docs.first()
        return this.createInstance().apply {
            title = book.title.toString()
            description = book.subtitle.toString()
            author = book.authorName?.first().toString()
        }
    }
}