package ron.example.demo.exception

import org.springframework.http.HttpStatus

open class TodoException(override val message: String, val status: HttpStatus) : RuntimeException(message)
