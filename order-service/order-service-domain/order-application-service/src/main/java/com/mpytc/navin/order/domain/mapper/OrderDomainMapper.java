package com.mpytc.navin.order.domain.mapper;

import com.mpytc.navin.order.domain.dto.CommandOrderAddress;
import com.mpytc.navin.order.domain.dto.CommandOrderItem;
import com.mpytc.navin.order.domain.dto.CreateOrderCommand;
import com.mpytc.navin.order.domain.entity.Order;
import com.mpytc.navin.order.domain.entity.OrderItem;
import com.mpytc.navin.order.domain.valueobject.StreetAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDomainMapper {

    @Mapping(source = "customerId", target = "customerId.value")
    @Mapping(source = "businessId", target = "businessId.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "orderAddress", target = "streetAddress")
    Order creatOrderCommandToOrder(CreateOrderCommand createOrderCommand);

    StreetAddress commandOrderAddressToStreetAddress(CommandOrderAddress commandOrderAddress);

    @Mapping(source = "productId", target = "product.id.value")
    @Mapping(source = "price", target = "price.amount")
    @Mapping(source = "subTotal", target = "subTotal.amount")
    OrderItem commandOrderItemToOrder(CommandOrderItem commandOrderItem);
}
