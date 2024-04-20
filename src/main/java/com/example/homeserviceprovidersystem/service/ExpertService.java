package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.dto.ExpertDto.ExpertDto;
import com.example.homeserviceprovidersystem.entity.Expert;
import org.springframework.web.multipart.MultipartFile;

public interface ExpertService {
    Expert save(Long idSubDuty, MultipartFile multipartFile, ExpertDto expertDto);
}
