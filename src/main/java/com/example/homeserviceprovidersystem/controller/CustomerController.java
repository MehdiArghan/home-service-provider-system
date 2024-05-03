package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.dto.customerDto.CustomerDto;
import com.example.homeserviceprovidersystem.dto.customerDto.CustomerDtoWithName;
import com.example.homeserviceprovidersystem.dto.expertDto.ExpertSummaryDto;
import com.example.homeserviceprovidersystem.dto.expertsuggestions.ExpertSuggestionsDto;
import com.example.homeserviceprovidersystem.dto.ordersDto.OrderSummaryDto;
import com.example.homeserviceprovidersystem.dto.ordersDto.OrdersDto;
import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDto;
import com.example.homeserviceprovidersystem.entity.Orders;
import com.example.homeserviceprovidersystem.mapper.*;
import com.example.homeserviceprovidersystem.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
public class CustomerController {
    final CustomerMapper customerMapper;
    final CustomerService customerService;
    final SubDutyMapper subDutyMapper;
    final SubDutyService subDutyService;
    final OrdersMapper ordersMapper;
    final OrdersService ordersService;
    final ExpertService expertService;
    final ExpertMapper expertMapper;
    final ExpertSuggestionsService expertSuggestionsService;
    final ExpertSuggestionsMapper expertSuggestionsMapper;

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDtoWithName customerDtoWithName) {
        CustomerDto savedCustomerDto = customerMapper.getCustomerToCustomerDto
                (customerService.save(customerMapper.getCustomerDtoWithNameToCustomer(customerDtoWithName)));
        return new ResponseEntity<>(savedCustomerDto, HttpStatus.CREATED);
    }

    @PostMapping("/saveOrders/{customerId}/{subDutyId}")
    public ResponseEntity<OrdersDto> saveOrders(
            @PathVariable Long customerId,
            @PathVariable Long subDutyId,
            @Valid @RequestBody OrderSummaryDto orderSummaryDto) {
        Orders orders = ordersMapper.getOrderSummaryDtoToOrders(orderSummaryDto);
        Orders savedOrders = ordersService.save(customerId, subDutyId, orders);
        return new ResponseEntity<>(ordersMapper.getOrdersToOrdersDto(savedOrders), HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllSubDuty")
    public ResponseEntity<List<SubDutyDto>> findAllSubDuty() {
        List<SubDutyDto> subDutyDtoList = subDutyService.findAll()
                .stream().map(subDutyMapper::getSubDutyToSubDutyDto).toList();
        return new ResponseEntity<>(subDutyDtoList, HttpStatus.FOUND);
    }

    @GetMapping(value = "/findAllExpert")
    public ResponseEntity<List<ExpertSummaryDto>> findAllExpert() {
        List<ExpertSummaryDto> expertSummaryDto =
                expertService.findAll().stream().map(expertMapper::getExpertToExpertSummaryDto).toList();
        return new ResponseEntity<>(expertSummaryDto, HttpStatus.FOUND);
    }

    @GetMapping(value = "/findAllOrderSuggestions/{customerId}/{subDutyId}")
    public ResponseEntity<List<ExpertSuggestionsDto>> findAllOrderSuggestions(
            @PathVariable Long customerId,
            @PathVariable Long subDutyId
    ) {
        List<ExpertSuggestionsDto> expertSuggestionsDto = expertSuggestionsService.findAllOrderSuggestions(customerId, subDutyId).stream()
                .map(expertSuggestionsMapper::getExpertSuggestionsToExpertSuggestionsDto).toList();
        return new ResponseEntity<>(expertSuggestionsDto, HttpStatus.FOUND);
    }
}
