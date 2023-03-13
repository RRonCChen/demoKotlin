package ron.example.demo.exception

import org.springframework.http.HttpStatus

data class TodoNotFoundException(override val message: String) : TodoException(message,HttpStatus.NOT_FOUND)
