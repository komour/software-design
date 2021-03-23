package ru.ifmo.komarov.sd3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.ifmo.komarov.sd3")
public class AppConfiguration {
}
