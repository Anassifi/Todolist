package com.pragmatic.anassifi.todolist.mysql.controller;

import com.pragmatic.anassifi.todolist.mysql.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static com.pragmatic.anassifi.todolist.mysql.util.AbstractControllerTest.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MysqlTodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void allTasks() throws Exception {
        mockMvc.perform(get("/mysql/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("TodoTask")))
                .andExpect(jsonPath("$[0].description", is("TodoDes project")))
                .andExpect(jsonPath("$[0].status", is(true)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("TodoTask2")))
                .andExpect(jsonPath("$[1].description", is("TodoDes project2")))
                .andExpect(jsonPath("$[1].status", is(false)));
    }

    @Test
    void newTodo() throws Exception {
        Todo taskToPost = new Todo("TodoTask", "TodoDes project", true);

        // Execute the POST request
        mockMvc.perform(post("/mysql/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(taskToPost)))

                // Validate the response code and content type
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.title", is("TodoTask")))
                .andExpect(jsonPath("$.description", is("TodoDes project")))
                .andExpect(jsonPath("$.status", is(true)));
    }

    @Test
    void getATodo() throws Exception {
        mockMvc.perform(get("/mysql/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("TodoTask")))
                .andExpect(jsonPath("$.description", is("TodoDes project")))
                .andExpect(jsonPath("$.status", is(true)));
    }

    @Test
    void taskNotFound() throws Exception {
        mockMvc.perform(get("/mysql/tasks/{id}", 444))
                .andExpect(status().isNotFound());
    }
}