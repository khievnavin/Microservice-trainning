package com.mpytc.navin.order.restapi.mapper;

import com.mpytc.navin.order.domain.entity.Business;
import com.mpytc.navin.order.domain.entity.Product;
import com.mpytc.navin.order.restapi.dto.BusinessResponse;
import com.mpytc.navin.order.restapi.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BusinessWebMapper {

    @Mapping(source = "id.value", target = "id")
    BusinessResponse businessToBusinessResponse(Business business);

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "price.amount", target = "price")
    ProductResponse productToProductResponse(Product product);

}
