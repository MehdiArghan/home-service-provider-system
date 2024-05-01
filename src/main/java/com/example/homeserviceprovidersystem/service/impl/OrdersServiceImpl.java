package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.customeException.CustomEntityNotFoundException;
import com.example.homeserviceprovidersystem.customeException.CustomResourceNotFoundException;
import com.example.homeserviceprovidersystem.entity.Customer;
import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.Wallet;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import com.example.homeserviceprovidersystem.repositroy.OrdersRepository;
import com.example.homeserviceprovidersystem.service.CustomerService;
import com.example.homeserviceprovidersystem.service.OrdersService;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import com.example.homeserviceprovidersystem.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerService customerService;
    private final SubDutyService subDutyService;
    private final WalletService walletService;

    @Autowired
    public OrdersServiceImpl(
            OrdersRepository ordersRepository,
            CustomerService customerService,
            SubDutyService subDutyService,
            WalletService walletService) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
        this.subDutyService = subDutyService;
        this.walletService = walletService;
    }

    @Override
    public Orders save(Long customerId, Long subDutyId, Orders orders) {
        Customer customer = customerService.findById(customerId);
        SubDuty subDuty = subDutyService.findById(subDutyId);
        validateOrder(customer, subDuty, orders);
        updateCustomerWallet(customer, orders);
        setOrderDetails(orders, customer, subDuty);
        return ordersRepository.save(orders);
    }

    private void validateOrder(Customer customer, SubDuty subDuty, Orders orders) {
        if (
                subDuty.getBasePrice() > orders.getProposedPrice() ||
                        customer.getWallet().getPrice() < orders.getProposedPrice()
        ) {
            throw new CustomBadRequestException("Proposed price must be greater than or equal to the base price of the subDuty or" +
                    "your account balance is insufficient");
        }
    }

    private void updateCustomerWallet(Customer customer, Orders orders) {
        Wallet wallet = customer.getWallet();
        wallet.setPrice(wallet.getPrice() - orders.getProposedPrice());
        walletService.save(wallet);
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
}
