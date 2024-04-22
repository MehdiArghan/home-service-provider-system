package com.example.homeserviceprovidersystem.mapper;

import com.example.homeserviceprovidersystem.dto.customerDto.CustomerDto;
import com.example.homeserviceprovidersystem.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    CustomerDto getCustomerToCustomerDto(Customer customer);

    Customer getCustomerDtoToCustomer(CustomerDto customerDto);
}
