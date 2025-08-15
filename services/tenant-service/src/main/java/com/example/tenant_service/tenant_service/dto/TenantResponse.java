package com.example.tenant_service.tenant_service.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantResponse {

    private UUID id;
    private String name;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}

