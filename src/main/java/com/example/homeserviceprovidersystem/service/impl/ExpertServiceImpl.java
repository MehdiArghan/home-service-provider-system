package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.dto.ExpertDto.ExpertDto;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.Wallet;
import com.example.homeserviceprovidersystem.entity.enums.ExpertStatus;
import com.example.homeserviceprovidersystem.mapper.ExpertMapper;
import com.example.homeserviceprovidersystem.repositroy.ExpertRepository;
import com.example.homeserviceprovidersystem.service.ExpertService;
import com.example.homeserviceprovidersystem.service.SubDutyService;
import com.example.homeserviceprovidersystem.service.WalletService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

@Service
public class ExpertServiceImpl implements ExpertService {
    private final SubDutyService subDutyService;
    private final ExpertRepository expertRepository;
    private final WalletService walletService;
    private final ExpertMapper expertMapper;
    private final Validator validator;

    @Autowired
    public ExpertServiceImpl(
            SubDutyService subDutyService,
            ExpertRepository expertRepository,
            WalletService walletService,
            ExpertMapper expertMapper,
            Validator validator) {
        this.subDutyService = subDutyService;
        this.expertRepository = expertRepository;
        this.walletService = walletService;
        this.expertMapper = expertMapper;
        this.validator = validator;
    }

    @Override
    public Expert save(Long idSubDuty, MultipartFile multipartFile, ExpertDto expertDto) {

    }
}
