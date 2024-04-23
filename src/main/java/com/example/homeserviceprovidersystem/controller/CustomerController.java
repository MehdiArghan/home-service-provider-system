package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.dto.customerDto.CustomerDto;
import com.example.homeserviceprovidersystem.dto.customerDto.CustomerDtoWithName;
import com.example.homeserviceprovidersystem.mapper.CustomerMapper;
import com.example.homeserviceprovidersystem.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {
    final CustomerMapper customerMapper;
    final CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDtoWithName customerDtoWithName) {
        CustomerDto savedCustomerDto = customerMapper.getCustomerToCustomerDto
                (customerService.save(customerMapper.getCustomerDtoWithNameToCustomer(customerDtoWithName)));
        return new ResponseEntity<>(savedCustomerDto, HttpStatus.CREATED);
    }
}
