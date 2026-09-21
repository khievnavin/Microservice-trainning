package com.mpytc.navin.order.persistence.repository;

import com.mpytc.navin.order.persistence.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BusinessJpaRepository extends JpaRepository<BusinessEntity, UUID>{
    List<BusinessEntity> findByBusinessId(UUID businessId);
}
