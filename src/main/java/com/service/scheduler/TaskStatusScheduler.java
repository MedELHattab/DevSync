package com.service.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.repository.TaskRepositoryImpl;

public class TaskStatusScheduler {

    private final TaskRepositoryImpl taskRepository = new TaskRepositoryImpl();
    private ScheduledExecutorService scheduler;

    public void scheduleTaskStatusUpdate() {
        scheduler = Executors.newScheduledThreadPool(1);

        // Execute the task immediately when the project starts
        System.out.println("Running task status update immediately...");
        taskRepository.updateOverdueTasks();

        // Schedule the task to run every day at midnight
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Running daily task status update...");
            taskRepository.updateOverdueTasks();
        }, getInitialDelay(), 24, TimeUnit.HOURS);
    }

    private long getInitialDelay() {
        // Calculate the delay until midnight
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.toLocalDate().plusDays(1).atStartOfDay();

        // Convert midnight to epoch seconds
        return midnight.toEpochSecond(ZoneOffset.UTC) - now.toEpochSecond(ZoneOffset.UTC);
    }

    public void shutdown() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();  // Restore the interrupted status
            }
        }
    }
}
