package ron.example.demo.service

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ron.example.demo.entity.Todo
import ron.example.demo.exception.TodoNotContentException
import ron.example.demo.exception.TodoNotFoundException
import ron.example.demo.repo.TodoRepo
import java.util.*

class TodoServiceImplTest {

    lateinit var openMocks: AutoCloseable

    @Mock
    private lateinit var todoRepo: TodoRepo

    @InjectMocks
    private lateinit var todoService: TodoServiceImpl

    @BeforeEach
    fun setUp() {
        openMocks = MockitoAnnotations.openMocks(this)
    }

    @AfterEach
    fun tearDown() {
        openMocks.close()
    }

    @Nested
    @DisplayName("test_createTodo_method")
    inner class TestCreateTodoMethod {

        @Test
        @DisplayName("when_create_todo_success")
        fun when_create_todo_success() {
            // given
            val givenTodo = Todo(0L, "test", false)

            given(todoRepo.save(givenTodo))
                .willReturn(Todo(1L, "test", false))

            // when
            val actual = todoService.createTodo(givenTodo)

            // then
            val except = Todo(1L, "test", false)
            assertThat(actual).isEqualTo(except)
        }

    }

    @Nested
    @DisplayName("test_updateTodo_method")
    inner class TestUpdateTodoMethod {

        @Test
        @DisplayName("when_update_todo_no_content_found")
        fun when_update_todo_no_content_found() {
            // given
            val givenTodo = Todo(777L, "test", false)

            given(todoRepo.findById(777L))
                .willReturn(Optional.empty())

            // when
            val thrown = catchThrowable { todoService.updateTodo(givenTodo) }

            assertThat(thrown).isEqualTo(TodoNotContentException("todo (id=777) no content found"))
        }

        @Test
        @DisplayName("when_update_todo_success")
        fun when_update_todo_success() {
            // given
            val givenTodo = Todo(777L, "test", true)

            given(todoRepo.findById(777L))
                .willReturn(Optional.ofNullable(Todo(777L, "test", false)))

            given(todoRepo.save(Todo(777L, "test", true)))
                .willReturn(Todo(777L, "test", true))

            // then
            val acutal = todoService.updateTodo(givenTodo)

            // then
            val except = Todo(777L, "test", true)
            assertThat(acutal).isEqualTo(except)

        }

    }

    @Nested
    @DisplayName("test_deleteTodo_method")
    inner class TestDeleteTodoMethod {

        @Test
        @DisplayName("when_delete_todo_no_content_found")
        fun when_delete_todo_no_content_found() {
            // given
            val givenId = 777L

            given(todoRepo.findById(777L))
                .willReturn(Optional.empty())

            // when
            val thrown = catchThrowable { todoService.deleteTodo(givenId) }

            assertThat(thrown).isEqualTo(TodoNotContentException("todo (id=777) no content found"))
        }

        @Test
        @DisplayName("when_delete_todo_success")
        fun when_delete_todo_success() {
            // given
            val givenId = 777L

            given(todoRepo.findById(777L))
                .willReturn(Optional.ofNullable(Todo(777L, "test", false)))

            willDoNothing()
                .given(todoRepo)
                .deleteById(givenId)

            // when
            val actual = todoService.deleteTodo(givenId)

            // then
            assertThat(actual).isEqualTo(777L)
        }

    }

    @Nested
    @DisplayName("test_getTodoById_method")
    inner class TestGetTodoByIdMethod {

        @Test
        @DisplayName("when_find_by_id_todo_no_found")
        fun when_find_by_id_todo_no_found() {
            // given
            val givenId = 777L

            given(todoRepo.findById(777L))
                .willReturn(Optional.empty())

            // when
            val thrown = catchThrowable { todoService.getTodoById(givenId) }

            assertThat(thrown).isEqualTo(TodoNotFoundException("todo (id=777) no found"))
        }

        @Test
        @DisplayName("when_find_by_id_found")
        fun when_find_by_id_found() {
            // given
            val givenId = 777L

            given(todoRepo.findById(777L))
                .willReturn(Optional.ofNullable(Todo(777L, "test", false)))

            // when
            val actual = todoService.getTodoById(givenId)

            // then
            assertThat(actual).isEqualTo(Todo(777L, "test", false))
        }

    }


    @Nested
    @DisplayName("test_getAllTodoList_method")
    inner class TestGetAllTodoListMethod {

        @Test
        @DisplayName("when_find_all_todo_no_found")
        fun when_find_all_todo_no_found() {
            // given
            given(todoRepo.findAll())
                .willReturn(emptyList())

            // when
            val actual = todoService.getAllTodoList()

            // then
            assertThat(actual).isEmpty()
        }

        @Test
        @DisplayName("when_find_all_todo_found")
        fun when_find_all_todo_found() {
            // given
            given(todoRepo.findAll())
                .willReturn(
                    mutableListOf(
                        Todo(777L, "test", true),
                        Todo(888L, "test2", true)
                    )
                )

            // when
            val actual = todoService.getAllTodoList()

            // then
            assertThat(actual)
                .isEqualTo(
                    mutableListOf(
                        Todo(777L, "test", true),
                        Todo(888L, "test2", true)
                    )
                )
        }

    }

}