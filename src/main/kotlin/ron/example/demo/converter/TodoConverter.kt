package ron.example.demo.converter

import ron.example.demo.dto.TodoDTO
import ron.example.demo.entity.Todo

object TodoConverter {
    fun convert(dto: TodoDTO): Todo = Todo(id = dto.id, description = dto.description, completed = dto.completed)

    fun convert(todo: Todo): TodoDTO = TodoDTO(id = todo.id, description = todo.description, completed = todo.completed)
}