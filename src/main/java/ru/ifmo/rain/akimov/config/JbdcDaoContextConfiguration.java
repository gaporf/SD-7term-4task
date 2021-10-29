package ru.ifmo.rain.akimov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ifmo.rain.akimov.dao.TodoListJbdcDao;

import javax.sql.DataSource;

@Configuration
public class JbdcDaoContextConfiguration {
    @Bean
    public TodoListJbdcDao todoListJbdcDao(DataSource dataSource) {
        return new TodoListJbdcDao(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:todolist.db");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }
}