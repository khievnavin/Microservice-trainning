package com.mpytc.navin.order.usecase;

import com.mpytc.navin.order.domain.dto.CreateOrderCommand;
import com.mpytc.navin.order.domain.dto.CreateOrderResult;
import com.mpytc.navin.order.domain.entity.Business;
import com.mpytc.navin.order.domain.entity.Product;
import com.mpytc.navin.order.domain.exception.OrderDomainException;
import com.mpytc.navin.order.domain.port.output.BusinessRepository;
import com.mpytc.navin.order.domain.port.output.CustomerRepository;
import com.mpytc.navin.order.domain.port.output.OrderRepository;
import com.mpytc.navin.order.domain.valueobject.BusinessId;
import com.mpytc.navin.order.domain.valueobject.Money;
import com.mpytc.navin.order.domain.valueobject.ProductId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;


@Component
@Slf4j  //lombok: create log object when compl
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand){
        log.info("executing createOrderUseCase:{}", createOrderCommand);

        //validate customer
        customerRepository.findCustomer(createOrderCommand.customerId())
                .orElseThrow(() -> new OrderDomainException(
                        "Customer not found with ID:"+
                                createOrderCommand.customerId()));

        //validate business
        List<Product> products = createOrderCommand.items().stream()
                .map(commandOrderItem -> Product.builder()
                        .id(new ProductId(commandOrderItem.productId()))
                        .price(new Money(commandOrderItem.price()))
                        .build())
                .toList();

        Business business = Business.builder()
                        .id(new BusinessId(createOrderCommand.businessId()))
                        .products(products)
                        .build();

        business = businessRepository.findBusiness(business)
                .orElseThrow(() -> new OrderDomainException("Business not found with ID:"+ createOrderCommand.businessId()));


        log.info("Found business: {}", business);

        return new CreateOrderResult(UUID.randomUUID());

    }

}

//Insert, Update ,Delete => Command - > Transaction
//Select -> Query -> Transaction read only
//Pattern: CQRS = Command Query Responsibility Segregation