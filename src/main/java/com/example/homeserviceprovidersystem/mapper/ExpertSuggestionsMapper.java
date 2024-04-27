package com.example.homeserviceprovidersystem.mapper;

import com.example.homeserviceprovidersystem.dto.expertsuggestions.ExpertSuggestionsDto;
import com.example.homeserviceprovidersystem.dto.expertsuggestions.ExpertSuggestionsSummaryDto;
import com.example.homeserviceprovidersystem.entity.ExpertSuggestions;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpertSuggestionsMapper {
    ExpertSuggestions getExpertSuggestionsSummaryDtoToExpertSuggestions(ExpertSuggestionsSummaryDto expertSuggestionsSummaryDto);

    ExpertSuggestionsDto getExpertSuggestionsToExpertSuggestionsDto(ExpertSuggestions expertSuggestions);
}
