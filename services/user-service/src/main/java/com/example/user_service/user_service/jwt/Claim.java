package com.example.user_service.user_service.jwt;

public record Claim(String name, String tenantId, String userId, String schoolId) {}
