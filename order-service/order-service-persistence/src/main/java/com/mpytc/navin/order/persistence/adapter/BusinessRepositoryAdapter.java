package com.mpytc.navin.order.persistence.adapter;

import com.mpytc.navin.order.domain.entity.Business;
import com.mpytc.navin.order.domain.entity.Product;
import com.mpytc.navin.order.domain.port.output.BusinessRepository;
import com.mpytc.navin.order.domain.valueobject.BusinessId;
import com.mpytc.navin.order.domain.valueobject.Money;
import com.mpytc.navin.order.domain.valueobject.ProductId;
import com.mpytc.navin.order.persistence.entity.BusinessEntity;
import com.mpytc.navin.order.persistence.mapper.BusinessPersistenceMapper;
import com.mpytc.navin.order.persistence.repository.BusinessJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {

    private final BusinessJpaRepository businessJpaRepository;
    private final BusinessPersistenceMapper businessPersistenceMapper;

    @Override
    public Optional<Business> findBusiness(UUID businessId) {
        List<BusinessEntity> businessEntities = businessJpaRepository.findByBusinessId(businessId);

        if (businessEntities.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(businessPersistenceMapper.businessEntitiesToBusiness(businessId, businessEntities));
    }

}
