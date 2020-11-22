package ru.komarov.sd.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.komarov.sd.mvc.dao.TaskDao;
import ru.komarov.sd.mvc.dao.TasksListDao;

@Configuration
public class InMemoryDaoContextConfiguration {
    @Bean
    public TasksListDao tasksListDao() {
        return new TasksListDao();
    }

    @Bean
    public TaskDao taskDao() {
        return new TaskDao();
    }
}
