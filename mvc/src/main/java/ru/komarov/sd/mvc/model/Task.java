package ru.komarov.sd.mvc.model;

public class Task {
    private int id;
    private int tasksListId;
    private String name;
    private boolean isActive;

    public Task() {
    }

    public int getTasksListId() {
        return tasksListId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTasksListId(int taskListId) {
        this.tasksListId = taskListId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
