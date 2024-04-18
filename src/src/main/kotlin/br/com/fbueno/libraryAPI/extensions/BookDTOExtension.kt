package br.com.fbueno.libraryAPI.extensions

import br.com.fbueno.libraryAPI.models.BookModel
import br.com.fbueno.libraryAPI.models.dto.BookDTO

fun BookDTO.toModel(): BookModel {
    return BookModel(
        id = null,
        title = this.title,
        description = this.description,
        author = this.author
    )
}
