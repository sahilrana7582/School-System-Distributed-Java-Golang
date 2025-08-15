package com.example.tenant_service.tenant_service.dto;

import com.example.tenant_service.tenant_service.enums.TenantStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantUpdateRequest {

    private String name; // Optional
    private TenantStatus status; // Optional
}
