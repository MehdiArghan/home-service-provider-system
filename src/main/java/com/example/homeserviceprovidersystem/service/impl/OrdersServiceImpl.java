package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.customeException.CustomEntityNotFoundException;
import com.example.homeserviceprovidersystem.customeException.CustomResourceNotFoundException;
import com.example.homeserviceprovidersystem.dto.order.AddressRequest;
import com.example.homeserviceprovidersystem.dto.order.OrderRequest;
import com.example.homeserviceprovidersystem.dto.order.OrdersResponse;
import com.example.homeserviceprovidersystem.dto.subduty.SubDutyRequestWithName;
import com.example.homeserviceprovidersystem.entity.Address;
import com.example.homeserviceprovidersystem.entity.Customer;
import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import com.example.homeserviceprovidersystem.mapper.OrdersMapper;
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
    private final OrdersMapper ordersMapper;

    @Autowired
    public OrdersServiceImpl(
            OrdersRepository ordersRepository,
            CustomerService customerService,
            SubDutyService subDutyService,
            OrdersMapper ordersMapper) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
        this.subDutyService = subDutyService;
        this.ordersMapper = ordersMapper;
    }

    @Override
    public OrdersResponse save(OrderRequest request) {
        Customer customer = customerService.findByEmail(request.getCustomerEmail());
        SubDuty subDuty = subDutyService.findByName(request.getSubDutyName());
        if (subDuty.getBasePrice() > request.getProposedPrice()) {
            throw new CustomBadRequestException("Proposed price must be greater than or equal to the base price of the subDuty");
        }
        Address address = createAddress(request.getAddress());
        Orders orders = createOrders(request, customer, subDuty, address);
        return ordersMapper.orderToOrdersResponse(ordersRepository.save(orders));
    }

    private Address createAddress(AddressRequest addressRequest) {
        return new Address(
                addressRequest.getProvince(),
                addressRequest.getCity(),
                addressRequest.getStreet(),
                addressRequest.getPostalCode()
        );
    }

    private Orders createOrders(OrderRequest request, Customer customer, SubDuty subDuty, Address address) {
        Orders orders = new Orders();
        orders.setProposedPrice(request.getProposedPrice());
        orders.setJobDescription(request.getJobDescription());
        orders.setDateOfWork(request.getDateOfWork());
        orders.setTimeOfWord(request.getTimeOfWord());
        orders.setAddress(address);
        orders.setCustomer(customer);
        orders.setSubDuty(subDuty);
        orders.setExpert(null);
        orders.setOrderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SUGGESTION);
        return orders;
    }

    @Override
    public List<OrdersResponse> findAllOrderWaitingForSpecialistSuggestion(SubDutyRequestWithName request) {
        List<Orders> allOrdersByOrderStatus = ordersRepository.findAllOrdersByOrderStatus(
                request.getNameSubDuty(),
                OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SUGGESTION,
                OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SELECTION);
        if (allOrdersByOrderStatus.isEmpty()) {
            throw new CustomResourceNotFoundException("There is no result");
        } else {
            return allOrdersByOrderStatus.stream().map(ordersMapper::orderToOrdersResponse).toList();
        }
    }

    @Override
    public Orders findById(Long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("orders with this id was not found"));
    }

    @Override
    public List<OrdersResponse> findAllOrderWaitingForSpecialistToWorkPlace() {
        List<Orders> findAllOrder = ordersRepository.findAllByOrderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_TO_WORKPLACE);
        if (findAllOrder.isEmpty()) {
            throw new CustomResourceNotFoundException("There is no result");
        } else {
            return findAllOrder.stream().map(ordersMapper::orderToOrdersResponse).toList();
        }
    }
/*
    @Override
    public Order selectStartWork(Long orderId) {
        ordersRepository.findById(orderId);
    }*/
}
