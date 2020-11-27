package ru.komarov.sd.mvc.logic;

import ru.komarov.sd.mvc.dao.TaskDao;
import ru.komarov.sd.mvc.model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class DataFilter {
    private static final Map<String, DataFilter> filters = createFiltersMap();

    private static HashMap<String, DataFilter> createFiltersMap() {
        HashMap<String, DataFilter> filters = new HashMap<>();
        filters.put("all", new AllFilter());
        filters.put("active", new ActiveFilter());
        return filters;
    }

    public abstract List<Task> filter(int id, TaskDao taskDao);

    private static class AllFilter extends DataFilter {
        public List<Task> filter(int id, TaskDao taskDao) {
            return taskDao.getTasksByTasksListId(id);
        }
    }

    private static class ActiveFilter extends DataFilter {
        public List<Task> filter(int id, TaskDao taskDao) {
            return taskDao.getActiveTasksByTasksListId(id);
        }
    }

    public static Optional<DataFilter> getFilterByName(String name) {
        return Optional.ofNullable(filters.get(name));
    }
}
