package ru.ifmo.rain.akimov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ifmo.rain.akimov.dao.TodoListDao;
import ru.ifmo.rain.akimov.dao.TodoListInMemoryDao;

//@Configuration
public class InMemoryDaoContextConfiguration {
    @Bean
    public TodoListDao todoListDao() {
        return new TodoListInMemoryDao();
    }
}
