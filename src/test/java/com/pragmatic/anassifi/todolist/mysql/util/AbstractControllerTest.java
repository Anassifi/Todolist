package com.pragmatic.anassifi.todolist.mysql.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragmatic.anassifi.todolist.mysql.model.Todo;

public abstract class AbstractControllerTest {
    public static String asJsonString(Todo todo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(todo);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
