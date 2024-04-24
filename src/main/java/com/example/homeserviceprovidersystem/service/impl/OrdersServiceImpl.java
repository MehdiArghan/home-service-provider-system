package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.dto.ordersDto.OrderSummaryDto;
import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.service.OrdersService;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Override
    public Orders save(Long customerId, Long expertId, Long subDutyId, OrderSummaryDto orderSummaryDto) {
        return null;
    }
}
