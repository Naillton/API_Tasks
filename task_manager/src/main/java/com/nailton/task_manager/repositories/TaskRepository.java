package com.nailton.task_manager.repositories;

import com.nailton.task_manager.models.Tasks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<Tasks, UUID> {

    @Query(value = "SELECT * FROM tasks WHERE id=?", nativeQuery = true)
    Tasks findTaskById(UUID id);

    @Query(value = "SELECT * FROM tasks WHERE status=?", nativeQuery = true)
    Tasks findTaskByStatus(String status);
}
