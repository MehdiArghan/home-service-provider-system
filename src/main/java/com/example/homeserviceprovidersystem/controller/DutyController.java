package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.service.DutyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DutyController {
    final DutyService dutyService;
}
