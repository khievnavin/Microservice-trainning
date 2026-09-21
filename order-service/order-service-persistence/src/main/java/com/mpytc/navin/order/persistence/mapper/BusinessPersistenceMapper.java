package com.mpytc.navin.order.persistence.mapper;

import com.mpytc.navin.order.domain.entity.Business;
import com.mpytc.navin.order.domain.entity.Product;
import com.mpytc.navin.order.domain.valueobject.BusinessId;
import com.mpytc.navin.order.persistence.entity.BusinessEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BusinessPersistenceMapper {

    @Mapping(source = "productId", target = "id.value")
    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productPrice", target = "price.amount")
    Product businessEntityToProduct(BusinessEntity businessEntity);

    default Business businessEntitiesToBusiness(UUID businessId, List<BusinessEntity> businessEntities) {
        if (businessEntities.isEmpty()) {
            return null;
        }

        List<Product> products = businessEntities.stream()
                .map(this::businessEntityToProduct)
                .toList();

        return Business.Builder.builder()
                .id(new BusinessId(businessId))
                .isactive(businessEntities.get(0).getBusinessActive())
                .products(products)
                .build();
    }

}
