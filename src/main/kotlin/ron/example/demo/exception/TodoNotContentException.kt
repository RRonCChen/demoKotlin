package ron.example.demo.exception

import org.springframework.http.HttpStatus

data class TodoNotContentException(override val message: String) : TodoException(message, HttpStatus.NO_CONTENT)
