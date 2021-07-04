package com.pragmatic.anassifi.todolist.mysql.service;

import com.pragmatic.anassifi.todolist.mysql.model.Todo;
import com.pragmatic.anassifi.todolist.mysql.model.User;
import com.pragmatic.anassifi.todolist.mysql.repository.TodoRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


class TodoServiceTest {

    /* Annotation used to inject the mocked objects */
    @InjectMocks
    TodoService todoService;

    /*
     *   We are creating a mock object here
     *   Fake class which we can instantiate the return results of it's   *   method by giving desire input and output
     *  */
    @Mock
    TodoRepository todoRepository;

    // id, name, username, password, email, todoList
    Todo task1 = new Todo();
    Todo task2 = new Todo();
    Todo task3 = new Todo();

    List<Todo> tasks = new ArrayList<>();

    User hakime = new User(1L, "Abdelhakim Nassifi", "Anassifi", "Anassifi2021@", "hakime@gmail.com", tasks);

    public Todo makeTask(Todo taskName , Long id, String name, String description){
        taskName.setId(id);
        taskName.setTitle(name);
        taskName.setDescription(description);
        taskName.setStatus(false); //not done
        taskName.setUser(hakime);

        return taskName;
    }

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("User todo test")
    void getTodoByUser() {

        tasks.add(makeTask(task1, 1L, "task1", "this is task1"));
        when(todoRepository.findTodoByUser(hakime)).thenReturn(Optional.of(task1));

        Optional<Todo> todoByUserTest = todoService.getTodoByUser(hakime);

        assertNotNull(todoByUserTest);
        assertEquals("task1", task1.getTitle(), "Test passed successfully");
    }

    @Test
    void getTodoById() {
        makeTask(task2, 2L, "Task2", "this is task2");
        when(todoRepository.findById(2L)).thenReturn(Optional.of(task2));

        Optional<Todo> todoTest = todoService.getTodoById(2L);

        assertNotNull(todoTest);
        assertEquals("Task2", task2.getTitle());
    }

    @Test
    void addTodo() {
        makeTask(task3, 3L, "task3", "this is task3");
        doReturn(task3).when(todoRepository).save(any());
        Todo returnTask = todoService.addTodo(task3);

        Assertions.assertNotNull(task3, "The saved task should not be null");
        Assertions.assertEquals("task3", returnTask.getTitle(), "Task Found");
    }

    @Test
    @DisplayName("AllTodos Test")
    void getAllTodos() {
        Todo todo1 = new Todo(1L, "task1", "this is task1", false);
        Todo todo2 = new Todo(1L, "task2", "this is task2", false);
        doReturn(Arrays.asList(todo1, todo2)).when(todoRepository).findAll();

        List<Todo> tasks = todoService.getAllTodos();

        Assertions.assertEquals(2, tasks.size(), "You have 2 tasks going");
    }
}