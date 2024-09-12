package com.nailton.task_manager.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nailton.task_manager.models.User;
import com.nailton.task_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String secret;

    private String encodePass(String pass) {
        return new BCryptPasswordEncoder().encode(pass);
    }

    private boolean decodePass(String rawPass, String encodedPass) {
        return new BCryptPasswordEncoder().matches(rawPass, encodedPass);
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
                .plusHours(4)
                .toInstant(ZoneOffset.of("-04:00"));
    }

    private String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("API_TASK")
                .withSubject(user.getEmail())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("API_TASK")
                .build()
                .verify(token)
                .getSubject();
    }

    public List<User> getUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    public User getUserByID(UUID id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public void insertUser(User user) {
        String hashedpas = encodePass(user.getPassword());
        user.setPassword(hashedpas);
        this.userRepository.save(user);
    }

    public void updateUser(User user, UUID id) {
        User userToUpdate = this.getUserByID(id);
        String hashedpas = encodePass(user.getPassword());
        userToUpdate.setPassword(hashedpas);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        this.userRepository.save(userToUpdate);
    }

    public void deleteUser(UUID id) {
        this.userRepository.deleteById(id);
    }

    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public String login(String email, String password) {
        User userByEmail = this.userRepository.findByEmail(email);
        if (userByEmail != null) {
            boolean isTrue = this.decodePass(password, userByEmail.getPassword());
            if (isTrue) {
                return generateToken(userByEmail);
            } else {
                return "User not found";
            }
        } else {
            return "User not found";
        }

    }
}
