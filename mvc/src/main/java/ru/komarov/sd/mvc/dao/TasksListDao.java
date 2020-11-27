package ru.komarov.sd.mvc.dao;

import ru.komarov.sd.mvc.model.TasksList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TasksListDao {
    private final AtomicInteger lastTasksListId = new AtomicInteger(0);
    private final LinkedHashMap<Integer, TasksList> tasksLists = new LinkedHashMap<>();


    public void addTasksList(TasksList tasksList) {
        int id = lastTasksListId.incrementAndGet();
        tasksList.setId(id);
        this.tasksLists.put(id, tasksList);

    }

    public TasksList getTasksListById(int id) {
        return tasksLists.get(id);
    }

    public List<TasksList> getTasksList() {
        return List.copyOf(tasksLists.values());
    }

    public void removeTasksList(int id) {
        tasksLists.remove(id);
    }
}
