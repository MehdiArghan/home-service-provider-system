package com.example.homeserviceprovidersystem.controller;

import com.example.homeserviceprovidersystem.dto.ExpertDto.ExpertDto;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/expert")
public class ExpertController {
}
