package ron.example.demo.converter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ron.example.demo.dto.TodoDTO
import ron.example.demo.entity.Todo

class TodoConverterTest {

    @Test
    @DisplayName("when_todoDto_convert_into_todo")
    fun when_todoDto_convert_into_todo() {
        // given
        val given = TodoDTO(777, "testDescription", false)

        // when
        val actual = TodoConverter.convert(given)

        // then
        assertThat(actual).isEqualTo(Todo(777, "testDescription", false))
    }

    @Test
    @DisplayName("when_todo_convert_into_todoDto")
    fun when_todo_convert_into_todoDto() {
        // given
        val given = Todo(777, "testDescription", false)

        // when
        val actual = TodoConverter.convert(given)

        // then
        assertThat(actual).isEqualTo(TodoDTO(777, "testDescription", false))
    }
}