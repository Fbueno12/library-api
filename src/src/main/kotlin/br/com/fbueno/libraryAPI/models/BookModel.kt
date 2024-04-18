package br.com.fbueno.libraryAPI.models

import jakarta.persistence.*

@Entity(name = "Book")
class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?,

    @Column var title: String,
    @Column var description: String,
    @Column var author: String

)
