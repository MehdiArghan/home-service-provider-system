package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.entity.Orders;

import java.util.List;

public interface OrdersService {
    Orders save(Long customerId, Long expertId, Long subDutyId, Orders orders);

    List<Orders> findAllOrderWaitingForSpecialistSuggestion();
}
