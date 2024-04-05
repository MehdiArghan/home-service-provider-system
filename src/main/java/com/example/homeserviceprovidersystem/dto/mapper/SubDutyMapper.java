package com.example.homeserviceprovidersystem.dto.mapper;

import com.example.homeserviceprovidersystem.dto.SubDutyDto;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubDutyMapper {
    SubDutyDto getSubDutyToSubDutyDto(SubDuty subDuty);

    SubDuty getSubDutyDtoToSubDuty(SubDutyDto subDutyDto);
}
