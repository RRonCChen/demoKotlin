package ron.example.demo.exception

import org.springframework.http.HttpStatus

class TodoNotFoundException(message: String) : TodoException(message, HttpStatus.NOT_FOUND)
