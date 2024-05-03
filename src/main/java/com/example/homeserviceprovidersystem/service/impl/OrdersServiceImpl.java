package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.customeException.CustomEntityNotFoundException;
import com.example.homeserviceprovidersystem.customeException.CustomResourceNotFoundException;
import com.example.homeserviceprovidersystem.entity.Customer;
import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import com.example.homeserviceprovidersystem.repositroy.OrdersRepository;
import com.example.homeserviceprovidersystem.service.CustomerService;
import com.example.homeserviceprovidersystem.service.OrdersService;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerService customerService;
    private final SubDutyService subDutyService;

    @Autowired
    public OrdersServiceImpl(
            OrdersRepository ordersRepository,
            CustomerService customerService,
            SubDutyService subDutyService) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
        this.subDutyService = subDutyService;
    }

    @Override
    public Orders save(Long customerId, Long subDutyId, Orders orders) {
        Customer customer = customerService.findById(customerId);
        SubDuty subDuty = subDutyService.findById(subDutyId);
        if (subDuty.getBasePrice() > orders.getProposedPrice()) {
            throw new CustomBadRequestException("Proposed price must be greater than or equal to the base price of the subDuty");
        }
        setOrderDetails(orders, customer, subDuty);
        return ordersRepository.save(orders);
    }

    private void setOrderDetails(Orders orders, Customer customer, SubDuty subDuty) {
        orders.setCustomer(customer);
        orders.setSubDuty(subDuty);
        orders.setExpert(null);
        orders.setOrderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SUGGESTION);
    }

    @Override
    public List<Orders> findAllOrderWaitingForSpecialistSuggestion(Long subDutyId) {
        List<Orders> allOrdersByOrderStatus = ordersRepository.findAllOrdersByOrderStatus(
                subDutyId,
                OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SUGGESTION,
                OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SELECTION);
        if (allOrdersByOrderStatus.isEmpty()) {
            throw new CustomResourceNotFoundException("There is no result");
        } else {
            return allOrdersByOrderStatus;
        }
    }

    @Override
    public Orders findById(Long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("orders with this id was not found"));
    }

    @Override
    public List<Orders> findAllOrderWaitingForSpecialistToWorkPlace() {
        List<Orders> findAllOrder = ordersRepository.findAllByOrderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_TO_WORKPLACE);
        if (findAllOrder.isEmpty()) {
            throw new CustomResourceNotFoundException("There is no result");
        } else {
            return findAllOrder;
        }
    }
}
