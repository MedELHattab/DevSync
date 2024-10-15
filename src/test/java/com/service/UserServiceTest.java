package com.service;

import com.model.User;
import com.model.UserTokens;
import com.repository.UserRepository;
import com.repository.userTokensRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private userTokensRepositoryImpl userTokensRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    @Test
    public void testCreateUser() {
        // Create a dummy user
        User user = new User("johhsbdfn_doe", "password123", "John", "Doe", "ffgffxfg@example.com", null);
        UserTokens userTokens = new UserTokens();
        // Call the service method
        userService.createUser(user);

        // Verify interactions with repositories
        verify(userRepository).createUser(user);
        verify(userTokensRepository).createUserTokens(userTokens);
    }
}
