package com.listener;

import com.service.scheduler.TokenResetScheduler;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {

    private TokenResetScheduler tokenResetScheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize and start the token reset scheduler
        tokenResetScheduler = new TokenResetScheduler();
        tokenResetScheduler.scheduleTokenReset();
        System.out.println("TokenResetScheduler started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Stop the scheduler if necessary
        if (tokenResetScheduler != null) {
            tokenResetScheduler.shutdown(); // Implement a shutdown method in your scheduler
            System.out.println("TokenResetScheduler stopped.");
        }
    }
}
