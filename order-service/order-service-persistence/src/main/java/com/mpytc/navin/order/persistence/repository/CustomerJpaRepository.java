package com.mpytc.navin.order.persistence.repository;

import com.mpytc.navin.order.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CustomerJpaRepository extends JpaRepository <CustomerEntity, UUID> {


}
