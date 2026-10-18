package com.mpytc.navin.business.persistence.adapter;

import com.mpytc.navin.business.domain.entity.OrderApproval;
import com.mpytc.navin.business.persistence.entity.OrderApprovalEntity;
import com.mpytc.navin.business.persistence.mapper.OrderApprovalPersistenceMapper;
import com.mpytc.navin.business.persistence.repository.OrderApprovalJpaRepository;
import com.mpytc.navin.business.port.output.OrderApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderApprovalRepositoryAdapter implements OrderApprovalRepository {

    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final OrderApprovalPersistenceMapper orderApprovalPersistenceMapper;

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        OrderApprovalEntity orderApprovalEntity = orderApprovalPersistenceMapper.orderApprovalToOrderApprovalEntity(orderApproval);

        return orderApprovalPersistenceMapper.orderApprovalEntityToOrderApproval(orderApprovalJpaRepository.save(orderApprovalEntity));
    }
}
