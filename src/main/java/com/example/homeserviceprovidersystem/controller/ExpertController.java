package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.dto.ExpertDto.ExpertDto;
import com.example.homeserviceprovidersystem.dto.ordersDto.OrderSummaryDto;
import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDto;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.mapper.ExpertMapper;
import com.example.homeserviceprovidersystem.mapper.OrdersMapper;
import com.example.homeserviceprovidersystem.mapper.SubDutyMapper;
import com.example.homeserviceprovidersystem.service.ExpertService;
import com.example.homeserviceprovidersystem.service.OrdersService;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/expert")
public class ExpertController {
    final SubDutyService subDutyService;
    final SubDutyMapper subDutyMapper;
    final ExpertMapper expertMapper;
    final ExpertService expertService;
    final OrdersService ordersService;
    final OrdersMapper ordersMapper;

    @PostMapping("/addExpert/{idSubDuty}")
    public ResponseEntity<ExpertDto> saveExpert(
            @PathVariable Long idSubDuty,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("picture") MultipartFile multipartFile
    ) {
        Expert savedExpert = expertService.save(idSubDuty, multipartFile, new ExpertDto(firstName, lastName, email, password));
        return new ResponseEntity<>(expertMapper.getExpertToExpertDto(savedExpert), HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllSubDuty")
    public ResponseEntity<List<SubDutyDto>> findAllSubDuty() {
        List<SubDutyDto> subDutyDtoList = subDutyService.findAll()
                .stream().map(subDutyMapper::getSubDutyToSubDutyDto).toList();
        return new ResponseEntity<>(subDutyDtoList, HttpStatus.FOUND);
    }

    @GetMapping(value = "findAllOrders")
    public ResponseEntity<List<OrderSummaryDto>> findAllOrders() {
        List<OrderSummaryDto> orders = ordersService.findAllOrderWaitingForSpecialistSuggestion()
                .stream().map(ordersMapper::getOrdersToOrderSummaryDto).toList();
        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }
}
