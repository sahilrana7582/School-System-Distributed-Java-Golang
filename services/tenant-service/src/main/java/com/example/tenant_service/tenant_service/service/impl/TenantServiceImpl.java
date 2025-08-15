package com.example.tenant_service.tenant_service.service.impl;

import com.example.error.BaseException;
import com.example.tenant_service.tenant_service.dto.*;
import com.example.tenant_service.tenant_service.entity.Tenant;
import com.example.tenant_service.tenant_service.mapper.TenantMapper;
import com.example.tenant_service.tenant_service.repository.TenantRepository;
import com.example.tenant_service.tenant_service.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;


    @Override
    public TenantResponse createTenant(TenantCreateRequest request) {
        Tenant tenant = TenantMapper.toEntity(request);
        Tenant savedTenant = tenantRepository.save(tenant);
        return TenantMapper.toResponse(savedTenant);

    }

    @Override
    public TenantResponse getTenantById(UUID tenantId) {
        Tenant tenant = tenantRepository.findTenantByID(tenantId)
                .orElseThrow(() -> new BaseException(
                        "Tenant not found",
                        "No tenant found with id: " + tenantId
                ));

        return TenantMapper.toResponse(tenant);
    }

    @Override
    public TenantResponse updateTenant(UUID tenantId, TenantUpdateRequest request) {
        Tenant tenant = tenantRepository.findTenantByID(tenantId)
                .orElseThrow(() -> new BaseException(
                        "Tenant not found",
                        "No tenant found with id: " + tenantId
                ));

        TenantMapper.updateEntity(request, tenant);
        return TenantMapper.toResponse(tenant);
    }

    @Override
    public void deleteTenant(UUID tenantId) {
        tenantRepository.deleteById(tenantId);
    }



    @Override
    public List<TenantResponse> listAllTenants() {
        return tenantRepository.findAll()
                .stream()
                .map(TenantMapper::toResponse)
                .toList();
    }
}
