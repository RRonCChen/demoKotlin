package ron.example.demo.exception

import org.springframework.http.HttpStatus

open class TodoException(override val message: String, open val status: HttpStatus) : RuntimeException(message)
