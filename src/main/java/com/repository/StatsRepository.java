package com.repository;

import java.util.Map;

public interface StatsRepository {
    Map<String, Map<String, Double>> getTagStatusStats();
}
