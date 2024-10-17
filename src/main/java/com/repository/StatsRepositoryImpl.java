package com.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsRepositoryImpl implements StatsRepository {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myJPAUnit");

    @Override
    public Map<String, Map<String, Double>> getTagStatusStats() {
        Map<String, Map<String, Double>> tagStatusStats = new HashMap<>();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Fetch total task count to calculate percentages
        Long totalTaskCount = (Long) entityManager.createQuery("SELECT COUNT(t) FROM Task t").getSingleResult();

        // SQL query for Java 11
        String sql = "SELECT t.name AS tagName, task.status AS status, COUNT(task.id) AS taskCount " +
                "FROM Task task " +
                "JOIN task.tags t " +
                "GROUP BY t.name, task.status " +
                "ORDER BY t.name, task.status";

        TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
        List<Object[]> results = query.getResultList();

        // Process results to build the statistics
        for (Object[] result : results) {
            String tagName = (String) result[0];
            String status = (String) result[1];
            Number taskCountNumber = (Number) result[2];  // Use Number to handle both Long and Double
            Long taskCount = taskCountNumber.longValue();  // Convert to long

            // Calculate percentage
            double percentage = (totalTaskCount == 0) ? 0.0 : (taskCount * 100.0 / totalTaskCount);

            // Update the tag status map
            tagStatusStats
                    .computeIfAbsent(tagName, k -> new HashMap<>())
                    .put(status, percentage);
        }

        // Ensure every tag has all statuses (even if 0%)
        for (String tag : tagStatusStats.keySet()) {
            Map<String, Double> statuses = tagStatusStats.get(tag);
            for (String status : List.of("Pending", "Completed", "Overdue")) {
                statuses.putIfAbsent(status, 0.0); // Default to 0.0 if status not present
            }
        }

        entityManager.close();
        return tagStatusStats;
    }
}
