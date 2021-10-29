package ru.ifmo.rain.akimov.model;

public class TodoItem {
    private int id;
    private int listId;
    private String name;
    private int isDone;

    public TodoItem() {
    }

    public TodoItem(int id, int listId, String name, int isDone) {
        this.id = id;
        this.listId = listId;
        this.name = name;
        this.isDone = isDone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getListId() {
        return listId;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public int isDone() {
        return isDone;
    }
}
