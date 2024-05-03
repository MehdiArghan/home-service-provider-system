package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.entity.ExpertSuggestions;

import java.util.List;

public interface ExpertSuggestionsService {
    ExpertSuggestions save(Long expertId, Long ordersId, ExpertSuggestions expertSuggestions);

    List<ExpertSuggestions> findAllOrderSuggestions(Long customerId, Long subDutyId);
    ExpertSuggestions findById(Long id);

    ExpertSuggestions selectExpertSuggestion(Long customerId, Long expertSuggestionId);
}
