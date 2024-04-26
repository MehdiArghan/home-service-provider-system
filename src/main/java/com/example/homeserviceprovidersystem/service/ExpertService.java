package com.example.homeserviceprovidersystem.service;

import com.example.homeserviceprovidersystem.dto.ExpertDto.ExpertDto;
import com.example.homeserviceprovidersystem.entity.Expert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExpertService {
    Expert save(Long idSubDuty, MultipartFile multipartFile, ExpertDto expertDto);

    List<Expert> findAllDisableExperts();

    Expert expertConfirmation(Long id);

    Expert findById(Long id);
}
