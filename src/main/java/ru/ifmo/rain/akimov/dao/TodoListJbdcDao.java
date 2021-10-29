package ru.ifmo.rain.akimov.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ru.ifmo.rain.akimov.model.TodoItem;
import ru.ifmo.rain.akimov.model.TodoList;

import javax.sql.DataSource;
import java.util.List;

public class TodoListJbdcDao extends JdbcDaoSupport implements TodoListDao {
    public TodoListJbdcDao(DataSource dataSource) {
        super();
        setDataSource(dataSource);
        String sql = "create table if not exists TodoLists(Id integer primary key autoincrement not null, Name varchar(50) not null)";
        getJdbcTemplate().update(sql);
        sql = "create table if not exists TodoItems(Id integer primary key autoincrement not null, ListId integer not null, Name varchar(50) not null, IsDone integer not null)";
        getJdbcTemplate().update(sql);
    }

    @Override
    public void addTodoList(TodoList todoList) {
        String sql = "insert into TodoLists (Name) values (?)";
        getJdbcTemplate().update(sql, todoList.getName());
    }

    @Override
    public List<TodoList> getTodoLists() {
        String sql = "select Id, Name from TodoLists";
        List<TodoList> todoLists = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TodoList.class));
        for (TodoList todoList : todoLists) {
            List<TodoItem> items = getTodoItems(todoList.getId());
            for (TodoItem todoItem : items) {
                todoList.addItem(todoItem);
            }
        }
        return todoLists;
    }

    @Override
    public TodoList getList(int id) {
        String sql = "select Id, Name from TodoLists where id = " + id;
        TodoList todoList = (TodoList) getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TodoList.class)).get(0);
        List<TodoItem> items = getTodoItems(todoList.getId());
        for (TodoItem todoItem : items) {
            todoList.addItem(todoItem);
        }
        return todoList;
    }

    @Override
    public void deleteTodoList(int id) {
        String sql = "delete from TodoLists where Id = ?";
        getJdbcTemplate().update(sql, id);
        sql = "delete from TodoItems where ListId = ?";
        getJdbcTemplate().update(sql, id);
    }

    @Override
    public void addTodoItem(int todoListId, TodoItem todoItem) {
        String sql = "insert into TodoItems (ListId, Name, IsDone) values (?, ?, 0)";
        getJdbcTemplate().update(sql, todoListId, todoItem.getName());
    }

    @Override
    public List<TodoItem> getTodoItems(int todoListId) {
        String sql = "select Id, ListId, Name, IsDone from TodoItems where ListId = " + todoListId;
        List<TodoItem> res = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TodoItem.class));
        return res;
    }

    @Override
    public TodoItem getTodoItem(int todoListId, int todoItemId) {
        String sql = "select Id, ListId, name, IsDone from TodoItems where Id = " + todoItemId;
        return (TodoItem) getJdbcTemplate().query(sql, new BeanPropertyRowMapper(TodoItem.class)).get(0);
    }

    @Override
    public void deleteTodoItem(int todoListId, int todoItemId) {
        String sql = "delete from TodoItems where Id = " + todoItemId;
        getJdbcTemplate().update(sql);
    }

    @Override
    public void invertItem(int todoListId, int todoItemId) {
        TodoItem todoItem = getTodoItem(todoListId, todoItemId);
         String sql = "update TodoItems set IsDone = " + (1 - todoItem.isDone()) + " where Id = " + todoItemId;
        getJdbcTemplate().update(sql);
    }
}
