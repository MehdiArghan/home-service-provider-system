package com.example.homeserviceprovidersystem.mapper;

import com.example.homeserviceprovidersystem.dto.dutyDto.DutyDto;
import com.example.homeserviceprovidersystem.entity.Duty;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DutyMapper {
    DutyDto getDutytoDutyDto(Duty duty);

    Duty getDutyDtoToDuty(DutyDto dutyDto);
}
