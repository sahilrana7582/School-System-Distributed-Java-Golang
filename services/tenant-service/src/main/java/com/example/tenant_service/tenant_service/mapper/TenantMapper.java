package com.example.tenant_service.tenant_service.mapper;

import com.example.tenant_service.tenant_service.dto.*;
import com.example.tenant_service.tenant_service.entity.Tenant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TenantMapper {

    public static Tenant toEntity(TenantCreateRequest req) {
        return req == null ? null : Tenant.builder()
                .name(req.getName())
                .status(req.getStatus())
                .build();
    }

    public static TenantResponse toResponse(Tenant tenant) {
        return tenant == null ? null : TenantResponse.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .status(tenant.getStatus())
                .createdAt(tenant.getCreatedAt())
                .updatedAt(tenant.getUpdatedAt())
                .build();
    }

    public static void updateEntity(TenantUpdateRequest req, Tenant tenant) {
        if (req == null || tenant == null) return;
        if (req.getName() != null) tenant.setName(req.getName());
        if (req.getStatus() != null) tenant.setStatus(req.getStatus());
    }
}
