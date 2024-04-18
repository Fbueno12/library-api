package br.com.fbueno.libraryAPI.models.dto

data class BookDTO(
    val title: String,
    val description: String? = null,
    val author: String? = null
)