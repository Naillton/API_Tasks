package com.nailton.task_manager.services;

import com.nailton.task_manager.models.Tasks;
import com.nailton.task_manager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Tasks> getTasks() {
        return (List<Tasks>) this.taskRepository.findAll();
    }

    public Tasks getTaskById(UUID id) {
        return this.taskRepository.findById(id).orElse(null);
    }

    public Tasks getTaskByStatus(String status) {
        return this.taskRepository.findTaskByStatus(status);
    }

    public void insertTask(Tasks task) {
        this.taskRepository.save(task);
    }

    public void updateTask(Tasks task, Tasks taskToUpdate) {
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setStatus(task.getStatus());
        this.taskRepository.save(taskToUpdate);
    }

    public void deleteTask(UUID id) {
        this.taskRepository.deleteById(id);
    }

    public void deleteAllTasks() {
        this.taskRepository.deleteAll();
    }
}
