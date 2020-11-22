package ru.komarov.sd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.komarov.sd.mvc.dao.TaskDao;
import ru.komarov.sd.mvc.dao.TasksListDao;
import ru.komarov.sd.mvc.logic.DataFilter;
import ru.komarov.sd.mvc.model.Task;
import ru.komarov.sd.mvc.model.TasksList;

import java.util.List;
import java.util.Optional;

@Controller
public class TasksListController {
    private final TasksListDao tasksListDao;
    private final TaskDao taskDao;

    public TasksListController(TasksListDao tasksListDao, TaskDao taskDao) {
        this.tasksListDao = tasksListDao;
        this.taskDao = taskDao;
    }

    @RequestMapping(value = "/tasksLists/go-back", method = RequestMethod.GET)
    public String goToMainPage() {
        return "redirect:/tasksLists";
    }

    @RequestMapping(value = "/add-tasksList", method = RequestMethod.POST)
    public String addTasksList(@ModelAttribute("tasksList") TasksList tasksList) {
        tasksListDao.addTasksList(tasksList);
        return "redirect:/tasksLists";
    }

    @RequestMapping(value = "/tasksLists", method = RequestMethod.GET)
    public String getTasksList(ModelMap map) {
        prepareModelMap(map, tasksListDao.getTasksList());
        return "index";
    }

    @RequestMapping(value = "/tasksLists/{id}", method = RequestMethod.GET)
    public String getTasksListTasks(ModelMap map, @PathVariable int id) {
        prepareModelMap(map, tasksListDao.getTasksListById(id), taskDao.getTasksByTasksListId(id));
        return "tasksList";
    }

    @RequestMapping(value = "/tasksLists/{id}/delete", method = RequestMethod.GET)
    public String deleteTasksList(@PathVariable int id) {
        tasksListDao.removeTasksList(id);
        return "redirect:/tasksLists";
    }

    @RequestMapping(value = "/tasksLists/{id}/filter-tasks", method = RequestMethod.GET)
    public String getProducts(@PathVariable int id, @RequestParam String filter, ModelMap map) {
        Optional<DataFilter> dataFilter = DataFilter.getFilterByName(filter);
        dataFilter.ifPresent(value -> prepareModelMap(map, tasksListDao.getTasksListById(id), value.filter(id, taskDao)));
        return "tasksList";
    }

    private void prepareModelMap(ModelMap map, List<TasksList> tasksLists) {
        map.addAttribute("tasksLists", tasksLists);
        map.addAttribute("tasksList", new TasksList());
    }

    private void prepareModelMap(ModelMap map, TasksList tasksList, List<Task> tasks) {
        map.addAttribute("currentTasksList", tasksList);
        map.addAttribute("currentTasks", tasks);
        map.addAttribute("task", new Task());
    }
}
