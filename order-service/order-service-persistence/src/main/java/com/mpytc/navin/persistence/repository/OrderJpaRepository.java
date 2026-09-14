package com.mpytc.navin.persistence.repository;

import com.mpytc.navin.persistence.entity.OrderEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//Benefit of using Spring Data JPA => Relational DB
//1. Boilerplate code
//2. Abstraction Repository
//3. Derived Query Method (auto generate SQL)
//4. Object Relational Mapping (ORM) Hibernate
//5. Specification (dynamic query)

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
