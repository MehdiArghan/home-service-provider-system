package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.entity.Orders;

public interface OrdersService {
    Orders save(Long customerId, Long expertId, Long subDutyId, Orders orders);
}
