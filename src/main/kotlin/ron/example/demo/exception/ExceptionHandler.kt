package ron.example.demo.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ron.example.demo.model.ResBody

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(TodoException::class)
    fun handleTodoNotFoundException(ex: TodoException): ResponseEntity<ResBody<Any?>> {
        return ResponseEntity(ResBody(ex.status.value(), ex.message, null), ex.status)
    }

}