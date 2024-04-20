package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.dto.ExpertDto.ExpertDto;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.service.ExpertService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Override
    public Expert save(Long idSubDuty, MultipartFile multipartFile, ExpertDto expertDto) {
        return null;
    }
}
