package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.entity.Duty;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.repositroy.DutyRepository;
import com.example.homeserviceprovidersystem.repositroy.SubDutyRepository;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SubDutyServiceImpl implements SubDutyService {
    protected SubDutyRepository subDutyRepository;
    protected DutyRepository dutyRepository;

    @Autowired
    public SubDutyServiceImpl(SubDutyRepository subDutyRepository, DutyRepository dutyRepository) {
        this.subDutyRepository = subDutyRepository;
        this.dutyRepository = dutyRepository;
    }

    @Override
    public SubDuty save(SubDuty subDuty, String nameDuty) {
        Optional<SubDuty> foundSubDuty = subDutyRepository.findByName(subDuty.getName());
        Optional<Duty> foundDuty = dutyRepository.findByName(nameDuty);
        if (foundSubDuty.isEmpty() && foundDuty.isPresent()) {
            SubDuty savedSubDuty = subDutyRepository.save(subDuty);
            Duty dutyAvailable = foundDuty.get();
            Set<SubDuty> subDuties = new HashSet<>();
            subDuties.add(savedSubDuty);
            dutyAvailable.setSubDuties(subDuties);
            dutyRepository.save(dutyAvailable);
            return savedSubDuty;
        } else {
            throw new RuntimeException();
        }
    }
}
