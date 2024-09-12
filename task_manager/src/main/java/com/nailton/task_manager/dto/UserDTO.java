package com.nailton.task_manager.dto;

import com.nailton.task_manager.models.User;


public record UserDTO(String username, String email, String password) {
    public User toEntity() {
        return new User(username, email, password);
    }
}
