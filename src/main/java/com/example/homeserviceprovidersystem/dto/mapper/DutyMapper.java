package com.example.homeserviceprovidersystem.dto.mapper;

import com.example.homeserviceprovidersystem.dto.DutyDto;
import com.example.homeserviceprovidersystem.dto.DutyDtoWithName;
import com.example.homeserviceprovidersystem.entity.Duty;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DutyMapper {
    DutyDto getDutytoDutyDto(Duty duty);

    Duty getDutyDtoToDuty(DutyDto dutyDto);

    DutyDtoWithName getDutyToDutyDtoWithName(Duty duty);

    Duty getDutyDtoWithNameToDuty(DutyDtoWithName dutyDtoWithName);
}
