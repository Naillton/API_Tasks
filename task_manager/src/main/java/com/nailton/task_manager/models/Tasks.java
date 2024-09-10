package com.nailton.task_manager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import java.util.UUID;
import java.time.LocalDate;

@Transactional
@Entity
@Table(name = "tasks")
public class Tasks {

    // Attributes

    @Id
    private final UUID id;

    private String title;

    private final LocalDate data;

    private String status;

    public Tasks(String title, String status) {
        this.id = UUID.randomUUID();
        this.data = LocalDate.now();
        this.title = title;
        this.status = status;
    }

    // Getters and setters

    public UUID getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
