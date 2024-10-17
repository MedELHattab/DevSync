package com.service;

import com.repository.StatsRepository;
import com.repository.StatsRepositoryImpl;

import java.util.Map;

public class StatService {

    private final StatsRepository statsRepository = new StatsRepositoryImpl();

    public Map<String, Map<String, Double>> displayTagStats() {
        Map<String, Map<String, Double>> tagStats = statsRepository.getTagStatusStats();

        for (String tag : tagStats.keySet()) {
            System.out.println("Tag: " + tag);
            Map<String, Double> statusStats = tagStats.get(tag);
            for (String status : statusStats.keySet()) {
                // Print percentage with one decimal precision
                System.out.printf("  Status: %s -> %.1f%%%n", status, statusStats.get(status));
            }
        }
        return tagStats;
    }
}
