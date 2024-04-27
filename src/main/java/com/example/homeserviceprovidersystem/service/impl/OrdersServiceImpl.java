package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.customeException.CustomEntityNotFoundException;
import com.example.homeserviceprovidersystem.customeException.CustomResourceNotFoundException;
import com.example.homeserviceprovidersystem.entity.*;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import com.example.homeserviceprovidersystem.repositroy.OrdersRepository;
import com.example.homeserviceprovidersystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final SubDutyService subDutyService;
    private final WalletService walletService;

    @Autowired
    public OrdersServiceImpl(
            OrdersRepository ordersRepository,
            CustomerService customerService,
            ExpertService expertService,
            SubDutyService subDutyService,
            WalletService walletService) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
        this.expertService = expertService;
        this.subDutyService = subDutyService;
        this.walletService = walletService;
    }

    @Override
    public Orders save(Long customerId, Long expertId, Long subDutyId, Orders orders) {
        Customer customer = customerService.findById(customerId);
        Expert expert = expertService.findById(expertId);
        SubDuty subDuty = subDutyService.findById(subDutyId);
        validateOrder(customer, expert, subDuty, subDutyId, orders);
        updateCustomerWallet(customer, orders);
        setOrderDetails(orders, customer, expert, subDuty);
        return ordersRepository.save(orders);
    }

    private void validateOrder(Customer customer, Expert expert, SubDuty subDuty, Long subDutyId, Orders orders) {
        if (
                expert.getSubDuties().stream().noneMatch(sb -> sb.getId().equals(subDutyId)) ||
                        subDuty.getBasePrice() > orders.getProposedPrice() ||
                        customer.getWallet().getPrice() < orders.getProposedPrice()
        ) {
            throw new CustomBadRequestException("Expert is not assigned to the selected subDuty or" +
                    "Proposed price must be greater than or equal to the base price of the subDuty or" +
                    "your account balance is insufficient");
        }
    }

    private void updateCustomerWallet(Customer customer, Orders orders) {
        Wallet wallet = customer.getWallet();
        wallet.setPrice(wallet.getPrice() - orders.getProposedPrice());
        walletService.save(wallet);
    }

    private void setOrderDetails(Orders orders, Customer customer, Expert expert, SubDuty subDuty) {
        orders.setCustomer(customer);
        orders.setExpert(expert);
        orders.setSubDuty(subDuty);
        orders.setOrderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SUGGESTION);
    }

    @Override
    public List<Orders> findAllOrderWaitingForSpecialistSuggestion(Long expertId) {
        List<Orders> allOrdersByOrderStatus = ordersRepository.findAllOrdersByOrderStatus(expertId,OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SUGGESTION);
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
