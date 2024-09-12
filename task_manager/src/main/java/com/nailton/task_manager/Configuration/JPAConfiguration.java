package com.nailton.task_manager.Configuration;

import com.nailton.task_manager.services.TaskService;
import com.nailton.task_manager.services.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.nailton.task_manager.repositories")
@EntityScan(basePackages = "com.nailton.task_manager.models")
public class JPAConfiguration {

    @Bean("userService")
    public UserService userService() {
        return new UserService();
    }

    @Bean("taskService")
    public TaskService taskService() {
        return new TaskService();
    }
}
