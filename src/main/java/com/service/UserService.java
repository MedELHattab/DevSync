
package com.service;

import com.model.User;
import com.model.UserTokens;  // Import UserTokens model
import com.repository.UserRepository;
import com.repository.UserRepositoryImpl;
import com.repository.userTokensRepositoryImpl;  // Import UserTokensRepository

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final userTokensRepositoryImpl userTokensRepository = new userTokensRepositoryImpl(); // Create instance of UserTokensRepository

    public void createUser(User user) {
        userRepository.createUser(user);

        // Create UserTokens for the newly created user
        UserTokens userTokens = new UserTokens();
        userTokens.setUser(user); // Associate with the created user
        userTokensRepository.createUserTokens(userTokens); // Save userTokens to the database
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
