package br.com.fbueno.libraryAPI.models

import jakarta.persistence.*
import org.hibernate.Hibernate

@Entity(name = "Book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?,

    @Column var title: String,
    @Column var description: String,
    @Column var author: String

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BookModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
