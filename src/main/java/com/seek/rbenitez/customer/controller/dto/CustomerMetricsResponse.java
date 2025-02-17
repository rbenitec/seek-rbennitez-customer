package com.seek.rbenitez.customer.controller.dto;

public record CustomerMetricsResponse(
        double averageAge,
        double ageStandardDeviation
) {
}
