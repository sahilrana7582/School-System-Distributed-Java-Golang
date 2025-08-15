package com.example.tenant_service.tenant_service.dto;

import com.example.tenant_service.tenant_service.enums.TenantStatus;
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
    private TenantStatus status;
    private Instant createdAt;
    private Instant updatedAt;

}

