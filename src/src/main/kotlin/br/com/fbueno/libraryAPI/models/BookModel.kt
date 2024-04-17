package br.com.fbueno.libraryAPI.models

import jakarta.persistence.*

@Entity(name = "Book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column val title: String,
    @Column val description: String,
    @Column val author: String

)
