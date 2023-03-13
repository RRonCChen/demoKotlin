package ron.example.demo.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ron.example.demo.converter.TodoConverter
import ron.example.demo.dto.TodoDTO
import ron.example.demo.model.ResBody
import ron.example.demo.service.TodoService

@Tag(name = "todo api")
@RestController
@RequestMapping("/api/v1")
class TodoController(val todoService: TodoService) {

    @GetMapping("/todo/{id}")
    fun getTodoById(@PathVariable("id") id: Long): ResponseEntity<ResBody<TodoDTO>> {
        val result = TodoConverter.convert(todoService.getTodoById(id))
        return ResponseEntity.ok(ResBody.getSuccessResBody(result))
    }

    @GetMapping("/todos")
    fun getAllTodoList(): ResponseEntity<ResBody<MutableList<TodoDTO>>> {
        val result = todoService.getAllTodoList()
            .map { TodoConverter.convert(it) }
            .toMutableList()
        return ResponseEntity.ok(ResBody.getSuccessResBody(result))
    }

    @PostMapping("/todo")
    fun createTodo(@RequestBody todoDTO: TodoDTO): ResponseEntity<ResBody<TodoDTO>> {
        val createdTodo = todoService.createTodo(TodoConverter.convert(todoDTO))
        val result = TodoConverter.convert(createdTodo)
        return ResponseEntity.ok(ResBody.getSuccessResBody(result))
    }

    @PutMapping("/todo")
    fun updateTodo(@RequestBody todoDTO: TodoDTO): ResponseEntity<ResBody<TodoDTO>> {
        val updatedTodo = todoService.updateTodo(TodoConverter.convert(todoDTO))
        val result = TodoConverter.convert(updatedTodo)
        return ResponseEntity.ok(ResBody.getSuccessResBody(result))
    }

    @DeleteMapping("/todo/{id}")
    fun deleteTodoById(@PathVariable("id") id: Long): ResponseEntity<ResBody<Long>> {
        return ResponseEntity.ok(ResBody.getSuccessResBody(todoService.deleteTodo(id)))
    }

}