package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.entity.ExpertSuggestions;
import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.repositroy.ExpertSuggestionsRepository;
import com.example.homeserviceprovidersystem.service.ExpertService;
import com.example.homeserviceprovidersystem.service.ExpertSuggestionsService;
import com.example.homeserviceprovidersystem.service.OrdersService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ExpertSuggestionsImpl implements ExpertSuggestionsService {
    private final ExpertService expertService;
    private final OrdersService ordersService;
    private final ExpertSuggestionsRepository expertSuggestionsRepository;

    public ExpertSuggestionsImpl(
            ExpertService expertService,
            OrdersService ordersService,
            ExpertSuggestionsRepository expertSuggestionsRepository
    ) {
        this.expertService = expertService;
        this.ordersService = ordersService;
        this.expertSuggestionsRepository = expertSuggestionsRepository;
    }

    @Override
    public ExpertSuggestions save(Long expertId, Long ordersId, ExpertSuggestions expertSuggestions) {
        Expert expert = expertService.findById(expertId);
        Orders orders = ordersService.findById(ordersId);
        validateExpertSuggestions(orders, expertSuggestions);
        setExpertSuggestionsDetails(expertSuggestions, orders, expert);
        return expertSuggestionsRepository.save(expertSuggestions);
    }

    private void validateExpertSuggestions(Orders orders, ExpertSuggestions expertSuggestions) {
        if (
                orders.getSubDuty().getBasePrice() > expertSuggestions.getProposedPrice() ||
                        expertSuggestions.getDateOfStartWork().isBefore(orders.getDateOfWork())
        ) {
            throw new CustomBadRequestException("Proposed price must be greater than or equal to the base price of the subDuty or" +
                    "Date of Start Work must be on or after the date of work");
        }
    }

    private void setExpertSuggestionsDetails(ExpertSuggestions expertSuggestionsDetails, Orders orders, Expert expert) {
        expertSuggestionsDetails.setOfferDate(LocalDate.now());
        expertSuggestionsDetails.setOfferTime(LocalTime.now());
        expertSuggestionsDetails.setOrders(orders);
        expertSuggestionsDetails.setExpert(expert);
    }
}
