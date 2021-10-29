package ru.ifmo.rain.akimov.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoList {
    private int id;
    private String name;
    private final List<TodoItem> todoItems = new CopyOnWriteArrayList<>();

    public TodoList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItem(TodoItem todoItem) {
        todoItems.add(todoItem);
    }

    public TodoItem getItem(int id) {
        for (TodoItem todoItem : todoItems) {
            if (todoItem.getId() == id) {
                return todoItem;
            }
        }
        return null;
    }

    public void deleteItem(int id) {
        for (int i = 0; i < todoItems.size(); i++) {
            if (todoItems.get(i).getId() == id) {
                todoItems.remove(i);
                break;
            }
        }
    }

    public List<TodoItem> getItems() {
        return List.copyOf(todoItems);
    }
}
