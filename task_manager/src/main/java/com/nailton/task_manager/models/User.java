package com.nailton.task_manager.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {

    // Attributes

    @Id
    private final UUID id;

    @Column(name = "username")
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id")
    private final List<Tasks> tasks;

    public User(String username, String email, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
        this.tasks = List.of();
    }

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks.add(tasks);
    }
}
