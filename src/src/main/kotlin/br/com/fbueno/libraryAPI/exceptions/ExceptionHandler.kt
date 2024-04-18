package br.com.fbueno.libraryAPI.exceptions

import br.com.fbueno.libraryAPI.config.BOOK_ALREADY_EXISTS_ERROR_MESSAGE
import br.com.fbueno.libraryAPI.config.BOOK_NOT_FOUND_ERROR_MESSAGE
import br.com.fbueno.libraryAPI.config.BOOK_TITLE_ALREADY_EXISTS_ERROR_MESSAGE
import br.com.fbueno.libraryAPI.models.dto.DefaultErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun bookAlreadyExistsException(ex: BookAlreadyExistsException): ResponseEntity<DefaultErrorDTO> {
        val error = DefaultErrorDTO(
            code = HttpStatus.BAD_REQUEST.value(),
            message = BOOK_ALREADY_EXISTS_ERROR_MESSAGE
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun bookTitleAlreadyExistsException(ex: BookTitleAlreadyExistsException): ResponseEntity<DefaultErrorDTO> {
        val error = DefaultErrorDTO(
            code = HttpStatus.BAD_REQUEST.value(),
            message = BOOK_TITLE_ALREADY_EXISTS_ERROR_MESSAGE
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun bookNotFoundException(ex: BookNotFoundException): ResponseEntity<DefaultErrorDTO> {
        val error = DefaultErrorDTO(
            code = HttpStatus.NOT_FOUND.value(),
            message = BOOK_NOT_FOUND_ERROR_MESSAGE
        )
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

}