package ron.example.demo.service

import ron.example.demo.entity.Todo

interface TodoService {

    fun createTodo(todo: Todo): Todo

    fun updateTodo(todo: Todo): Todo

    fun deleteTodo(id: Long) : Long

    fun getTodoById(id: Long): Todo

    fun getAllTodoList(): MutableList<Todo>
}