package com.example.tenant_service.tenant_service.service;

import com.example.tenant_service.tenant_service.enums.TenantStatus;

import java.util.List;
import java.util.UUID;

public interface TenantService {


    TenantResponse createTenant(TenantCreateRequest request);


    TenantResponse getTenantById(UUID tenantId);

    TenantResponse updateTenant(UUID tenantId, TenantUpdateRequest request);


    void deleteTenant(UUID tenantId);

    TenantResponse changeTenantStatus(UUID tenantId, TenantStatus status);


    List<TenantResponse> listAllTenants();

    List<TenantResponse> searchTenantsByName(String namePart);
}
