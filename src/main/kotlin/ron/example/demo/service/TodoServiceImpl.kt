package ron.example.demo.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ron.example.demo.entity.Todo
import ron.example.demo.exception.TodoNotContentException
import ron.example.demo.exception.TodoNotFoundException
import ron.example.demo.repo.TodoRepo

@Service
class TodoServiceImpl(val todoRepo: TodoRepo) : TodoService {

    override fun createTodo(todo: Todo): Todo {
        return todoRepo.save(todo)
    }

    override fun updateTodo(todo: Todo): Todo {
        todoRepo.findByIdOrNull(todo.id) ?: throw TodoNotContentException("todo (id=${todo.id}) no content found")
        return todoRepo.save(todo)
    }

    override fun deleteTodo(id: Long) : Long {
        todoRepo.findByIdOrNull(id) ?: throw TodoNotContentException("todo (id=${id}) no content found")
        todoRepo.deleteById(id)
        return id
    }

    override fun getTodoById(id: Long): Todo {
        return todoRepo.findByIdOrNull(id) ?: throw TodoNotFoundException("todo (id=${id}) no found")
    }

    override fun getAllTodoList(): MutableList<Todo> {
        return todoRepo.findAll()
    }
}