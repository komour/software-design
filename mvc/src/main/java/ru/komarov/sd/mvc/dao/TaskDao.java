package ru.komarov.sd.mvc.dao;

import ru.komarov.sd.mvc.model.Task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class TaskDao {
    private final AtomicInteger lastTaskId = new AtomicInteger(0);
    private final LinkedHashMap<Integer, Task> tasks = new LinkedHashMap<>();

    public void addTask(int tasksListId, Task task) {
        int id = lastTaskId.incrementAndGet();
        task.setId(id);
        task.setTasksListId(tasksListId);
        task.setActive(true);
        tasks.put(id, task);
    }

    public void updateTask(int tasksListId, int taskId, Task task) {
        task.setTasksListId(tasksListId);
        task.setId(taskId);
        tasks.put(taskId, task);
    }

    public void removeTask(int taskId) {
        tasks.remove(taskId);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public List<Task> getAllTasks() {
        return List.copyOf(tasks.values());
    }

    public List<Task> getTasksByTasksListId(int tasksListId) {
        return tasks.values().stream().filter(task -> task.getTasksListId() == tasksListId).collect(Collectors.toList());
    }

    public List<Task> getActiveTasksByTasksListId(int tasksListId) {
        return getTasksByTasksListId(tasksListId).stream().filter(Task::isActive).collect(Collectors.toList());
    }

    public List<Task> getInactiveTasksByTasksListId(int tasksListId) {
        return getTasksByTasksListId(tasksListId).stream().filter(not(Task::isActive)).collect(Collectors.toList());
    }
}
