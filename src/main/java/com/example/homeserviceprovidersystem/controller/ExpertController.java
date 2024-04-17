package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDto;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.mapper.SubDutyMapper;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/expert")
public class ExpertController {
    protected SubDutyService subDutyService;
    protected SubDutyMapper subDutyMapper;

    @GetMapping(value = "/findAllSubDuty")
    public ResponseEntity<List<SubDutyDto>> findAllSubDuty() {
        List<SubDuty> subDutyList = subDutyService.findAll();
        List<SubDutyDto> subDutyDtoList = subDutyList.stream().map(subDutyMapper::getSubDutyToSubDutyDto).toList();
        return new ResponseEntity<>(subDutyDtoList, HttpStatus.FOUND);
    }
}
