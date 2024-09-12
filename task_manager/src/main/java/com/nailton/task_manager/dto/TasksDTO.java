package com.nailton.task_manager.dto;

import com.nailton.task_manager.models.Tasks;

public record TasksDTO(String title, String status) {
    public Tasks toEntity() {
        return new Tasks(title, status);
    }
}
