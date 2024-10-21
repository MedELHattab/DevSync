package com.listener;

import com.service.scheduler.TaskStatusScheduler;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class TaskSchedulerListener implements ServletContextListener {

    private TaskStatusScheduler taskStatusScheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize and start the task status scheduler
        taskStatusScheduler = new TaskStatusScheduler();
        taskStatusScheduler.scheduleTaskStatusUpdate();
        System.out.println("TaskStatusScheduler started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Stop the scheduler if necessary
        if (taskStatusScheduler != null) {
            taskStatusScheduler.shutdown();
            System.out.println("TaskStatusScheduler stopped.");
        }
    }
}
