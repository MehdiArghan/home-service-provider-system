package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.entity.SubDuty;

import java.util.List;

public interface SubDutyService {
    SubDuty save(SubDuty subDuty, String nameDuty);

    List<SubDuty> findAll();

    SubDuty updateDescription(SubDuty subDuty, Long id);

    SubDuty updateBasePrice(SubDuty subDuty, Long id);

    SubDuty findById(Long id);
}
