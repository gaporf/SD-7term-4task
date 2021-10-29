package ru.ifmo.rain.akimov.dao;

import ru.ifmo.rain.akimov.model.TodoItem;
import ru.ifmo.rain.akimov.model.TodoList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoListInMemoryDao implements TodoListDao {
    private final AtomicInteger listId = new AtomicInteger(0);
    private final AtomicInteger itemId = new AtomicInteger(0);
    private final List<TodoList> todoLists = new CopyOnWriteArrayList<>();

    @Override
    public void addTodoList(TodoList todoList) {
        int id = listId.incrementAndGet();
        todoList.setId(id);
        todoLists.add(todoList);
    }

    @Override
    public List<TodoList> getTodoLists() {
        return List.copyOf(todoLists);
    }

    @Override
    public TodoList getList(int id) {
        for (TodoList todoList : todoLists) {
            if (todoList.getId() == id) {
                return todoList;
            }
        }
        return null;
    }

    @Override
    public void deleteTodoList(int id) {
        for (int i = 0; i < todoLists.size(); i++) {
            if (todoLists.get(i).getId() == id) {
                todoLists.remove(i);
                break;
            }
        }
    }

    @Override
    public void addTodoItem(int todoListId, TodoItem todoItem) {
        int id = itemId.incrementAndGet();
        todoItem.setId(id);
        TodoList todoList = getList(todoListId);
        todoList.addItem(todoItem);
    }

    @Override
    public List<TodoItem> getTodoItems(int todoListId) {
        TodoList todoList = getList(todoListId);
        return todoList.getItems();
    }

    @Override
    public TodoItem getTodoItem(int todoListId, int todoItemId) {
        TodoList todoList = getList(todoListId);
        return todoList.getItem(todoItemId);
    }

    @Override
    public void deleteTodoItem(int todoListId, int todoItemId) {
        TodoList todoList = getList(todoListId);
        todoList.deleteItem(todoItemId);
    }

    @Override
    public void invertItem(int todoListId, int todoItemId) {
        TodoItem todoItem = getTodoItem(todoListId, todoItemId);
        todoItem.setIsDone(1 - todoItem.isDone());
    }
}
