package ru.komarov.sd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.komarov.sd.mvc.dao.TaskDao;
import ru.komarov.sd.mvc.model.Task;

@Controller
public class TaskController {
    private final TaskDao taskDao;

    public TaskController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/tasksLists/{id}/go-back", method = RequestMethod.GET)
    public String goBack() {
        return "redirect:/tasksLists/{id}";
    }

    @RequestMapping(value = "/tasksLists/{id}/to-the-main", method = RequestMethod.GET)
    public String toTheMainPage() {
        return "redirect:/tasksLists";
    }

    @RequestMapping(value = "/tasksLists/{id}/add-task", method = RequestMethod.POST)
    public String addTask(@PathVariable int id, @ModelAttribute("task") Task task) {
        taskDao.addTask(id, task);
        return "redirect:/tasksLists/{id}";
    }

    @RequestMapping(value = "/tasksLists/{id}/task/{taskId}", method = RequestMethod.GET)
    public String getTask(ModelMap map, @PathVariable int id, @PathVariable int taskId) {
        prepareModelMap(map, id, taskDao.getTaskById(taskId));
        return "task";
    }

    @RequestMapping(value = "/tasksLists/{id}/task/{taskId}", method = RequestMethod.POST)
    public String updateTask(@PathVariable int id, @PathVariable int taskId, @ModelAttribute("task") Task task) {
        taskDao.updateTask(id, taskId, task);
        return "redirect:/tasksLists/{id}/task/{taskId}";
    }

    @RequestMapping(value = "/tasksLists/{id}/task/{taskId}/delete", method = RequestMethod.GET)
    public String deleteTasksList(@PathVariable int id, @PathVariable int taskId) {
        taskDao.removeTask(taskId);
        return "redirect:/tasksLists/{id}";
    }


    private void prepareModelMap(ModelMap map, int tasksListId, Task task) {
        map.addAttribute("task", task);
        map.addAttribute("currentTasksListId", tasksListId);
    }
}
