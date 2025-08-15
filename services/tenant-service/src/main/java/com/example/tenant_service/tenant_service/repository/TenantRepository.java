package com.example.tenant_service.tenant_service.repository;

import com.example.tenant_service.tenant_service.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {

    @Query(value = """
            SELECT * FROM tenants t WHERE t.id = :id
            """, nativeQuery = true)
    Optional<Tenant> findTenantByID(@Param("id") UUID id);

    @Query("""
        SELECT t FROM Tenant t
        WHERE LOWER(t.name) = LOWER(:name)
        """)
    List<Tenant> findTenantByNameIgnoreCase(@Param("name") String name);
}
