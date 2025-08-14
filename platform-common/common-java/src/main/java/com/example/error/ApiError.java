package com.example.error;

import java.time.Instant;
import java.util.Map;

public record ApiError(
        String code,
        String message,
        Instant timestamp,
        String path,
        Map<String, Object> details
) {}
