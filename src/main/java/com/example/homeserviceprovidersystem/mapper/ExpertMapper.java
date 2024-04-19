package com.example.homeserviceprovidersystem.mapper;

import com.example.homeserviceprovidersystem.dto.ExpertDto.ExpertDto;
import com.example.homeserviceprovidersystem.entity.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExpertMapper {
    ExpertDto getExpertToExpertDto(Expert expert);

    Expert getExpertDtoToExpert(ExpertDto expertDto);
}
