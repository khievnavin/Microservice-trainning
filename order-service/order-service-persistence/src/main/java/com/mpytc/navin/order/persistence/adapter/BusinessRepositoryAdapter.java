package com.mpytc.navin.order.persistence.adapter;

import com.mpytc.navin.order.domain.entity.Business;
import com.mpytc.navin.order.domain.entity.Product;
import com.mpytc.navin.order.domain.port.output.BusinessRepository;
import com.mpytc.navin.order.domain.valueobject.BusinessId;
import com.mpytc.navin.order.domain.valueobject.Money;
import com.mpytc.navin.order.domain.valueobject.ProductId;
import com.mpytc.navin.order.persistence.entity.BusinessEntity;
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

    @Override
    public Optional<Business> findBusiness(UUID businessId) {
        List<BusinessEntity> findBusinessEntities =
                businessJpaRepository.findByBusinessId(businessId);
        if (findBusinessEntities.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toBusiness(businessId, findBusinessEntities));
    }

    private Business toBusiness(UUID businessId, List<BusinessEntity> businessEntities) {
        List<Product> products = businessEntities.stream()
                .map(this::toProduct)
                .toList();

        return Business.Builder.builder()
                .id(new BusinessId(businessId))
                .isactive(businessEntities.get(0).getBusinessActive())
                .products(products)
                .build();
    }

    private Product toProduct(BusinessEntity businessEntity) {
        Product.Builder builder = Product.Builder.builder()
                .id(new ProductId(businessEntity.getProductId()))
                .name(businessEntity.getProductName());
        builder.price = new Money(businessEntity.getProductPrice());
        return builder.build();
    }

}
