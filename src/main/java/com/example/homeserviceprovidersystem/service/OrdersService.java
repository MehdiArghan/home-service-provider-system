package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.dto.order.OrderRequest;
import com.example.homeserviceprovidersystem.dto.order.OrdersResponse;
import com.example.homeserviceprovidersystem.dto.subduty.SubDutyRequestWithName;
import com.example.homeserviceprovidersystem.entity.Orders;

import java.util.List;

public interface OrdersService {
    OrdersResponse save(OrderRequest request);

    List<OrdersResponse> findAllOrderWaitingForSpecialistSuggestion(SubDutyRequestWithName request);

    Orders findById(Long id);

    List<OrdersResponse> findAllOrderWaitingForSpecialistToWorkPlace();

    /*Order selectStartWork(Long orderId);*/
}
