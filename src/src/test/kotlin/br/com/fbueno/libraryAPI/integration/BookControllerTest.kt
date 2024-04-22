package br.com.fbueno.libraryAPI.integration

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.file.Files

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun `api should save a book`() {
        mvc.perform(
            MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.readJsonFile("bookRequest.json"))
        ).andExpect(status().isOk)
    }

    private fun readJsonFile(file: String): String {
        val path = "__files/$file"
        return String(Files.readAllBytes(ClassPathResource(path).file.toPath()))
    }
}
