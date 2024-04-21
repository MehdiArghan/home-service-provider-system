package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.dto.ExpertDto.ExpertDto;
import com.example.homeserviceprovidersystem.dto.dutyDto.DutyDto;
import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDto;
import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDtoWithBasePrice;
import com.example.homeserviceprovidersystem.dto.subDutyDto.SubDutyDtoWithDescription;
import com.example.homeserviceprovidersystem.entity.Duty;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.mapper.DutyMapper;
import com.example.homeserviceprovidersystem.mapper.ExpertMapper;
import com.example.homeserviceprovidersystem.mapper.SubDutyMapper;
import com.example.homeserviceprovidersystem.service.DutyService;
import com.example.homeserviceprovidersystem.service.ExpertService;
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
    final ExpertService expertService;
    final ExpertMapper expertMapper;

    @PostMapping(value = "/addDuty")
    public ResponseEntity<DutyDto> saveDuty(@Valid @RequestBody DutyDto dutyDto) {
        Duty duty = dutyMapper.getDutyDtoToDuty(dutyDto);
        Duty savedDuty = dutyService.save(duty);
        return new ResponseEntity<>(dutyMapper.getDutytoDutyDto(savedDuty), HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllDuty")
    public ResponseEntity<List<DutyDto>> findAllDuty() {
        List<DutyDto> dutyDtoList = dutyService.findAll()
                .stream().map(dutyMapper::getDutytoDutyDto).toList();
        return new ResponseEntity<>(dutyDtoList, HttpStatus.FOUND);
    }

    @PostMapping(value = "/addSubDuty")
    public ResponseEntity<SubDutyDto> saveSubDuty(@Valid @RequestBody SubDutyDto subDutyDto,
                                                  @RequestParam String nameDuty) {
        SubDuty subDuty = subDutyMapper.getSubDutyDtoToSubDuty(subDutyDto);
        SubDuty savedSubDuty = subDutyService.save(subDuty, nameDuty);
        return new ResponseEntity<>(subDutyMapper.getSubDutyToSubDutyDto(savedSubDuty), HttpStatus.CREATED);
    }

    @GetMapping(value = "/findAllSubDuty")
    public ResponseEntity<List<SubDutyDto>> findAllSubDuty() {
        List<SubDutyDto> subDutyDtoList = subDutyService.findAll()
                .stream().map(subDutyMapper::getSubDutyToSubDutyDto).toList();
        return new ResponseEntity<>(subDutyDtoList, HttpStatus.FOUND);
    }

    @PostMapping(value = "/editDescriptionSubDuty/{id}")
    public ResponseEntity<SubDutyDto> updateDescriptionSubDuty(@Valid @RequestBody SubDutyDtoWithDescription subDutyDtoWithDescription,
                                                               @PathVariable Long id) {
        SubDuty subDuty = subDutyMapper.getSubDutyDtoWithDescriptionToSubDuty(subDutyDtoWithDescription);
        SubDuty updateSubDuty = subDutyService.updateDescription(subDuty, id);
        return new ResponseEntity<>(subDutyMapper.getSubDutyToSubDutyDto(updateSubDuty), HttpStatus.OK);
    }

    @PostMapping(value = "/editBasePriceSubDuty/{id}")
    public ResponseEntity<SubDutyDto> updateBasePriceSubDuty(@Valid @RequestBody SubDutyDtoWithBasePrice subDutyDtoWithBasePrice,
                                                             @PathVariable Long id) {
        SubDuty subDuty = subDutyMapper.getSubDutyDtoWithBasePriceToSubDuty(subDutyDtoWithBasePrice);
        SubDuty updateSubDuty = subDutyService.updateBasePrice(subDuty, id);
        return new ResponseEntity<>(subDutyMapper.getSubDutyToSubDutyDto(updateSubDuty), HttpStatus.OK);
    }

    @GetMapping("/findAllDisableExperts")
    public ResponseEntity<List<ExpertDto>> findAllDisableExperts() {
        List<ExpertDto> expertDtoList = expertService.findAllDisableExperts()
                .stream().map(expertMapper::getExpertToExpertDto).toList();
        return new ResponseEntity<>(expertDtoList, HttpStatus.FOUND);
    }
}