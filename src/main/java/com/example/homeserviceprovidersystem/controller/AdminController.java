package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.dto.dutyDto.DutyDto;
import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDto;
import com.example.homeserviceprovidersystem.entity.Duty;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.mapper.DutyMapper;
import com.example.homeserviceprovidersystem.mapper.SubDutyMapper;
import com.example.homeserviceprovidersystem.service.DutyService;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {
    final DutyService dutyService;
    final DutyMapper dutyMapper;
    final SubDutyService subDutyService;
    final SubDutyMapper subDutyMapper;

    @PostMapping(value = "/addDuty")
    public ResponseEntity<DutyDto> saveDuty(@Valid @RequestBody DutyDto dutyDto) {
        Duty duty = dutyMapper.getDutyDtoToDuty(dutyDto);
        Duty savedDuty = dutyService.save(duty);
        return new ResponseEntity<>(dutyMapper.getDutytoDutyDto(savedDuty), HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllDuty")
    public ResponseEntity<List<DutyDto>> findAllDuty() {
        List<Duty> dutyList = dutyService.findAll();
        List<DutyDto> dutyDtoList = dutyList.stream().map(dutyMapper::getDutytoDutyDto).toList();
        return new ResponseEntity<>(dutyDtoList, HttpStatus.FOUND);
    }

    @PostMapping(value = "/addSubDuty")
    public ResponseEntity<SubDutyDto> saveSubDuty(@Valid @RequestBody SubDutyDto subDutyDto,
                                           @RequestParam String nameDuty) {
        System.out.println(subDutyDto.getName()+subDutyDto.getDescription()+subDutyDto.getBasePrice());
        SubDuty subDuty = subDutyMapper.getSubDutyDtoToSubDuty(subDutyDto);
        SubDuty savedSubDuty = subDutyService.save(subDuty, nameDuty);
        return new ResponseEntity<>(subDutyMapper.getSubDutyToSubDutyDto(savedSubDuty), HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllSubDuty")
    public ResponseEntity<List<SubDutyDto>> findAllSubDuty() {
        List<SubDuty> subDutyList = subDutyService.findAll();
        List<SubDutyDto> subDutyDtoList = subDutyList.stream().map(subDutyMapper::getSubDutyToSubDutyDto).toList();
        return new ResponseEntity<>(subDutyDtoList, HttpStatus.FOUND);
    }
}
