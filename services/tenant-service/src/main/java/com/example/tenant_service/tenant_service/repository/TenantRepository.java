package com.example.tenant_service.tenant_service.repository;

import com.example.tenant_service.tenant_service.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {
}
