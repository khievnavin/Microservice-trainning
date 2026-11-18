package com.mpytc.navin.customer.domain.usecase;


import com.mpytc.navin.core.PageResponse;
import com.mpytc.navin.customer.domain.dto.CustomerResult;
import com.mpytc.navin.customer.domain.dto.GetCustomersQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCustomersUserCase {

    PageResponse<CustomerResult> execute(GetCustomersQuery query){
        log.info("Executing GetAllCustomersUserCase..... {}", query);
        return null;
    }

}
