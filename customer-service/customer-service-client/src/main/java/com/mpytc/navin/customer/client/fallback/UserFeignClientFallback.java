package com.mpytc.navin.customer.client.fallback;

import com.mpytc.navin.customer.client.dto.UserResponse;
import com.mpytc.navin.customer.client.exception.UserServiceUnavailableException;
import com.mpytc.navin.customer.client.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFeignClientFallback implements UserFeignClient {




    @Override
    public UserResponse getById(String id) {
       log.info("UserFeignClientFallback: getById: {}", id);

        // 1. Get Cache from Redis

        // *2. Require or Verify(payment, user)
        throw new UserServiceUnavailableException("user service unavailable now, please try again later!!");
    }

}
