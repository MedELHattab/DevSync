package com.repository;

import com.model.User;

import java.util.List;

public interface UserRepository {
    void createUser(User user);
    User readUser(Long id);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> listUsers();
}