package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.entity.ExpertSuggestions;

public interface ExpertSuggestionsService {
    ExpertSuggestions save(Long expertId, Long ordersId, ExpertSuggestions expertSuggestions);
}
