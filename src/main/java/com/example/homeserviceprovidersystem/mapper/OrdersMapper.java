package com.example.homeserviceprovidersystem.mapper;

import com.example.homeserviceprovidersystem.dto.ordersDto.OrderSummaryDto;
import com.example.homeserviceprovidersystem.dto.ordersDto.OrdersDto;
import com.example.homeserviceprovidersystem.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrdersMapper {
    OrdersDto getOrdersToOrdersDto(Orders orders);

    Orders getOrdersDtoToOrders(OrdersDto ordersDto);

    Orders getOrderSummaryDtoToOrders(OrderSummaryDto orderSummaryDto);

    OrderSummaryDto getOrdersToOrderSummaryDto(Orders orders);
}
