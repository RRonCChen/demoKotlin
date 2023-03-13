package ron.example.demo.repo

import org.springframework.data.jpa.repository.JpaRepository
import ron.example.demo.entity.Todo

interface TodoRepo : JpaRepository<Todo, Long> {
}