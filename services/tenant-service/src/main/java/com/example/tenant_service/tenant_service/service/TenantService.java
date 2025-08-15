package com.example.tenant_service.tenant_service.service;

import com.example.tenant_service.tenant_service.dto.*;

import java.util.List;
import java.util.UUID;

public interface TenantService {


    TenantResponse createTenant(TenantCreateRequest request);


    TenantResponse getTenantById(UUID tenantId);

    TenantResponse updateTenant(UUID tenantId, TenantUpdateRequest request);


    void deleteTenant(UUID tenantId);



    List<TenantResponse> listAllTenants();

}
