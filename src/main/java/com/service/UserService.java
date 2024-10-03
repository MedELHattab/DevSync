package com.service;

import com.model.User;
import com.repository.UserRepository;
import com.repository.UserRepositoryImpl;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public User readUser(long id) {
        return userRepository.readUser(id);
    }

    public List<User> listUsers() {
        return userRepository.listUsers();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}
