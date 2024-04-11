package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomResourceNotFoundException;
import com.example.homeserviceprovidersystem.customeException.CustomRuntimeException;
import com.example.homeserviceprovidersystem.entity.Duty;
import com.example.homeserviceprovidersystem.repositroy.DutyRepository;
import com.example.homeserviceprovidersystem.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyServiceImpl implements DutyService {
    protected DutyRepository dutyRepository;

    @Autowired
    public DutyServiceImpl(DutyRepository dutyRepository) {
        this.dutyRepository = dutyRepository;
    }

    @Override
    public Duty save(Duty duty) {
        Optional<Duty> foundDuty = dutyRepository.findByName(duty.getName());
        if (foundDuty.isEmpty()) {
            return dutyRepository.save(duty);
        } else {
            throw new CustomRuntimeException("Duty with name '" + duty.getName() + "'available");
        }
    }

    @Override
    public List<Duty> findAll() {
        List<Duty> dutyList = dutyRepository.findAll();
        if (dutyList.isEmpty()) {
            throw new CustomResourceNotFoundException("There is no result");
        } else {
            return dutyList;
        }
    }
}
