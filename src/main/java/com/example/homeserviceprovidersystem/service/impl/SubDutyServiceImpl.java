package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomEntityNotFoundException;
import com.example.homeserviceprovidersystem.customeException.CustomResourceNotFoundException;
import com.example.homeserviceprovidersystem.customeException.CustomRuntimeException;
import com.example.homeserviceprovidersystem.entity.Duty;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.repositroy.SubDutyRepository;
import com.example.homeserviceprovidersystem.service.DutyService;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubDutyServiceImpl implements SubDutyService {
    private final SubDutyRepository subDutyRepository;
    private final DutyService dutyService;

    @Autowired
    public SubDutyServiceImpl(SubDutyRepository subDutyRepository, DutyService dutyService) {
        this.subDutyRepository = subDutyRepository;
        this.dutyService = dutyService;
    }

    @Override
    public SubDuty save(SubDuty subDuty, String nameDuty) {
        Optional<SubDuty> foundSubDuty = subDutyRepository.findByName(subDuty.getName());
        Duty duty = dutyService.findByName(nameDuty);
        if (foundSubDuty.isEmpty()) {
            subDuty.setDuty(duty);
            return subDutyRepository.save(subDuty);
        } else {
            throw new CustomRuntimeException("the subDuty is available");
        }
    }

    @Override
    public List<SubDuty> findAll() {
        List<SubDuty> subDutyList = subDutyRepository.findAll();
        if (subDutyList.isEmpty()) {
            throw new CustomResourceNotFoundException("There is no result");
        } else {
            return subDutyList;
        }
    }

    @Override
    public SubDuty updateDescription(SubDuty subDuty, Long id) {
        Optional<SubDuty> foundSubDuty = subDutyRepository.findById(id);
        if (foundSubDuty.isEmpty()) {
            throw new CustomEntityNotFoundException("no subDuty was found with this id");
        } else {
            SubDuty existSubDuty = foundSubDuty.get();
            existSubDuty.setDescription(subDuty.getDescription());
            return subDutyRepository.save(existSubDuty);
        }
    }

    @Override
    public SubDuty updateBasePrice(SubDuty subDuty, Long id) {
        Optional<SubDuty> foundSubDuty = subDutyRepository.findById(id);
        if (foundSubDuty.isEmpty()) {
            throw new CustomEntityNotFoundException("no subDuty was found with this id");
        } else {
            SubDuty existSubDuty = foundSubDuty.get();
            existSubDuty.setBasePrice(subDuty.getBasePrice());
            return subDutyRepository.save(existSubDuty);
        }
    }

    @Override
    public SubDuty findById(Long id) {
        Optional<SubDuty> foundSubDuty = subDutyRepository.findById(id);
        if (foundSubDuty.isEmpty()) {
            throw new CustomEntityNotFoundException("no subDuty was found with this id");
        } else {
            return foundSubDuty.get();
        }
    }
}
