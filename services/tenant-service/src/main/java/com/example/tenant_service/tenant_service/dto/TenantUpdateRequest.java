package com.example.tenant_service.tenant_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantUpdateRequest {

    private String name; // Optional
    private String status; // Optional
}
