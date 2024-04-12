package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.entity.Duty;

import java.util.List;

public interface DutyService {
    Duty save(Duty duty);
    Duty findByName(String nameDuty);

    List<Duty> findAll();
}
