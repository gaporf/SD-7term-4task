package ru.ifmo.rain.akimov.dao;

import ru.ifmo.rain.akimov.model.TodoItem;
import ru.ifmo.rain.akimov.model.TodoList;

import java.util.List;

public interface TodoListDao {
    void addTodoList(TodoList todoList);

    List<TodoList> getTodoLists();

    TodoList getList(int todoListId);

    void deleteTodoList(int todoListId);

    void addTodoItem(int todoListId, TodoItem todoItem);

    List<TodoItem> getTodoItems(int todoListId);

    TodoItem getTodoItem(int todoListId, int todoItemId);

    void deleteTodoItem(int todoListId, int todoItemId);

    void invertItem(int todoListId, int todoItemId);
}
