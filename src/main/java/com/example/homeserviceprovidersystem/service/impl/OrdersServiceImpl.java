package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.entity.Customer;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import com.example.homeserviceprovidersystem.repositroy.OrdersRepository;
import com.example.homeserviceprovidersystem.service.CustomerService;
import com.example.homeserviceprovidersystem.service.ExpertService;
import com.example.homeserviceprovidersystem.service.OrdersService;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final SubDutyService subDutyService;

    @Autowired
    public OrdersServiceImpl(
            OrdersRepository ordersRepository,
            CustomerService customerService,
            ExpertService expertService,
            SubDutyService subDutyService) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
        this.expertService = expertService;
        this.subDutyService = subDutyService;
    }

    @Override
    public Orders save(Long customerId, Long expertId, Long subDutyId, Orders orders) {
        Customer customer = customerService.findById(customerId);
        Expert expert = expertService.findById(expertId);
        SubDuty subDuty = subDutyService.findById(subDutyId);
        if (expert.getSubDuties().stream().noneMatch(sb -> sb.getId().equals(subDutyId))
                || subDuty.getBasePrice() > orders.getProposedPrice()) {
            throw new CustomBadRequestException("Please enter the correct information");
        } else {
            orders.setCustomer(customer);
            orders.setExpert(expert);
            orders.setSubDuty(subDuty);
            orders.setOrderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SELECTION);
            return ordersRepository.save(orders);
        }
    }
}
