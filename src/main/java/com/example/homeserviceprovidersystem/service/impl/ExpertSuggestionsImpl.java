package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.customeException.CustomEntityNotFoundException;
import com.example.homeserviceprovidersystem.customeException.CustomResourceNotFoundException;
import com.example.homeserviceprovidersystem.entity.Customer;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.entity.ExpertSuggestions;
import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import com.example.homeserviceprovidersystem.repositroy.ExpertSuggestionsRepository;
import com.example.homeserviceprovidersystem.repositroy.OrdersRepository;
import com.example.homeserviceprovidersystem.service.CustomerService;
import com.example.homeserviceprovidersystem.service.ExpertService;
import com.example.homeserviceprovidersystem.service.ExpertSuggestionsService;
import com.example.homeserviceprovidersystem.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExpertSuggestionsImpl implements ExpertSuggestionsService {
    private final ExpertService expertService;
    private final OrdersService ordersService;
    private final ExpertSuggestionsRepository expertSuggestionsRepository;
    private final OrdersRepository ordersRepository;
    private final CustomerService customerService;

    @Autowired
    public ExpertSuggestionsImpl(
            ExpertService expertService,
            OrdersService ordersService,
            ExpertSuggestionsRepository expertSuggestionsRepository,
            OrdersRepository ordersRepository,
            CustomerService customerService) {
        this.expertService = expertService;
        this.ordersService = ordersService;
        this.expertSuggestionsRepository = expertSuggestionsRepository;
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
    }

    @Override
    public ExpertSuggestions save(Long expertId, Long ordersId, ExpertSuggestions expertSuggestions) {
        Expert expert = expertService.findById(expertId);
        Orders orders = ordersService.findById(ordersId);
        validateExpertSuggestions(expert, orders, expertSuggestions);
        setExpertSuggestionsDetails(expertSuggestions, orders, expert);
        ExpertSuggestions saveExpertSuggestion = expertSuggestionsRepository.save(expertSuggestions);
        updateOrdersStatus(orders);
        return saveExpertSuggestion;
    }

    private void validateExpertSuggestions(
            Expert expert,
            Orders orders,
            ExpertSuggestions expertSuggestions) {

        if (expert.getSubDuties().stream().noneMatch(subDuty -> orders.getSubDuty().getName().equals(subDuty.getName())) ||
                orders.getSubDuty().getBasePrice() > expertSuggestions.getProposedPrice()) {
            throw new CustomBadRequestException("Please select the order related to your specialty or " +
                    "Proposed price must be greater than or equal to the base price of the subDuty");
        }
        if (expertSuggestions.getDateOfStartWork().isBefore(orders.getDateOfWork())) {
            throw new CustomBadRequestException("Date of Start Work must be on or after the date of work");
        }
        if (expertSuggestions.getTimeOfStartWork().isBefore(orders.getTimeOfWord())) {
            throw new CustomBadRequestException("Time of Start Work must be on or after the Time of work");
        }

    }

    private void setExpertSuggestionsDetails(ExpertSuggestions expertSuggestionsDetails, Orders orders, Expert expert) {
        expertSuggestionsDetails.setOfferDate(LocalDate.now());
        expertSuggestionsDetails.setOfferTime(LocalTime.now());
        expertSuggestionsDetails.setOrders(orders);
        expertSuggestionsDetails.setExpert(expert);
    }

    private void updateOrdersStatus(Orders orders) {
        orders.setOrderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SELECTION);
        ordersRepository.save(orders);
    }

    @Override
    public List<ExpertSuggestions> findAllOrderSuggestions(Long customerId, Long subDutyId) {
        List<ExpertSuggestions> listOrderSuggestions =
                expertSuggestionsRepository.findAllOrderSuggestions(customerId, subDutyId, OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SELECTION);
        if (listOrderSuggestions.isEmpty()) {
            throw new CustomResourceNotFoundException("There is no result");
        } else {
            return listOrderSuggestions;
        }
    }

    @Override
    public ExpertSuggestions findById(Long id) {
        return expertSuggestionsRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("expertSuggestion with this id was not found"));
    }

    @Override
    public ExpertSuggestions selectExpertSuggestion(Long customerId, Long expertSuggestionId) {
        Customer customer = customerService.findById(customerId);
        ExpertSuggestions expertSuggestion = findById(expertSuggestionId);
        Orders orders = expertSuggestion.getOrders();
        if (!orders.getCustomer().getId().equals(customer.getId())) {
            throw new CustomBadRequestException("expertSuggestion is not related to this customer");
        }
        orders.setOrderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_TO_WORKPLACE);
        orders.setExpert(expertSuggestion.getExpert());
        ordersRepository.save(orders);
        return expertSuggestion;
    }
}
