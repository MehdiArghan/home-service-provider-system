package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.dto.DutyDto;
import com.example.homeserviceprovidersystem.dto.DutyDtoWithName;
import com.example.homeserviceprovidersystem.dto.mapper.DutyMapper;
import com.example.homeserviceprovidersystem.entity.Duty;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.service.DutyService;
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
@RequestMapping(value = "/admin")
public class AdminController {
    final DutyService dutyService;
    final DutyMapper dutyMapper;

    @PostMapping(value = "/addDuty")
    public ResponseEntity<DutyDto> save(@Valid @RequestBody DutyDtoWithName dutyDtoWithName) {
        Duty duty = dutyMapper.getDutyDtoWithNameToDuty(dutyDtoWithName);
        Duty savedDuty = dutyService.save(duty);
        return new ResponseEntity<>(dutyMapper.getDutytoDutyDto(savedDuty), HttpStatus.CREATED);
    }
}
