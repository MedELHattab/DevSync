package com.service.scheduler;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.repository.userTokensRepositoryImpl;

public class TokenResetScheduler {

    private final userTokensRepositoryImpl userTokensRepository = new userTokensRepositoryImpl();
    private ScheduledExecutorService scheduler;

    public void scheduleTokenReset() {
        scheduler = Executors.newScheduledThreadPool(2);

        // Schedule daily token reset every day
        scheduler.scheduleAtFixedRate(userTokensRepository::resetDailyTokens, 0, 1, TimeUnit.DAYS);  // Reset daily tokens every 24 hours

        // Schedule monthly token reset at the start of each month
        scheduler.scheduleAtFixedRate(() -> {
            if (LocalDate.now().getDayOfMonth() == 1) {  // Only reset on the first day of the month
                userTokensRepository.resetMonthlyTokens();
            }
        }, 0, 1, TimeUnit.DAYS);  // Check and reset monthly tokens once a day
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
