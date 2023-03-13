package ron.example.demo.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import ron.example.demo.model.ResBody

class ExceptionHandlerTest {

    private val exceptionHandler= ExceptionHandler()


    @Test
    fun when_handle_notFound_TodoException() {
        // given
        val notFoundException = TodoNotFoundException("Id : 777 not found")

        // when
        val actual = exceptionHandler.handleTodoNotFoundException(notFoundException)

        // then
        val except =
            ResponseEntity(ResBody<Any?>(404, "Id : 777 not found", null), HttpStatus.NOT_FOUND)

        assertThat(actual).isEqualTo(except)
    }

}