package com.mpytc.navin.persistence.repository;

import com.mpytc.navin.persistence.entity.OrderEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {


}
