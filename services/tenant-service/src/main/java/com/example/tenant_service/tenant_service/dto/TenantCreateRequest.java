package com.example.tenant_service.tenant_service.dto;

import com.example.tenant_service.tenant_service.enums.TenantStatus;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantCreateRequest {

    @NotBlank(message = "Tenant name is required")
    private String name;

    @NotNull(message = "Tenant status is required")
    private TenantStatus status;
}
