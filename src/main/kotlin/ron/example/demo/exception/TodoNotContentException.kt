package ron.example.demo.exception

import org.springframework.http.HttpStatus

class TodoNotContentException(message: String) : TodoException(message, HttpStatus.NO_CONTENT)
