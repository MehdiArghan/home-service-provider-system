package com.example.homeserviceprovidersystem.mapper;

import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDto;
import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDtoWithBasePrice;
import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDtoWithDescription;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubDutyMapper {
    SubDutyDto getSubDutyToSubDutyDto(SubDuty subDuty);

    SubDuty getSubDutyDtoToSubDuty(SubDutyDto subDutyDto);

    SubDuty getSubDutyDtoWithDescriptionToSubDuty(SubDutyDtoWithDescription subDutyDtoWithDescription);
    SubDuty getSubDutyDtoWithBasePriceToSubDuty(SubDutyDtoWithBasePrice subDutyDtoWithBasePrice);
}