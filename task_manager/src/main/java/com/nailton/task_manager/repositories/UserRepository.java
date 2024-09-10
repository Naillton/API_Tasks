package com.nailton.task_manager.repositories;

import com.nailton.task_manager.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @Query(value = "SELECT * FROM user WHERE email=? AND password=?", nativeQuery = true)
    User findByEmailAndPassword(String email, String password);
}
