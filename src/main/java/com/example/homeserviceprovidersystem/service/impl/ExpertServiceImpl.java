package com.example.homeserviceprovidersystem.service.impl;

import com.example.homeserviceprovidersystem.customeException.CustomBadRequestException;
import com.example.homeserviceprovidersystem.customeException.CustomResourceNotFoundException;
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
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
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
        expertRepository.findByEmail(expertDto.getEmail()).ifPresent(existingExpert -> {
            throw new CustomBadRequestException("Email already exists");
        });
        ExpertDto newExpertDto = updateExpertDto(idSubDuty, multipartFile, expertDto);
        validateExpertDto(newExpertDto);
        return expertRepository.save(expertMapper.getExpertDtoToExpert(newExpertDto));
    }

    private void validateExpertDto(ExpertDto newExpertDto) {
        Set<ConstraintViolation<ExpertDto>> violations = validator.validate(newExpertDto);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).toList();
            throw new CustomBadRequestException(String.join(",", errorMessages));
        }
    }

    private ExpertDto updateExpertDto(Long idSubDuty, MultipartFile multipartFile, ExpertDto expertDto) {
        SubDuty foundSubDuty = subDutyService.findById(idSubDuty);
        expertDto.setSubDuties(Collections.singleton(foundSubDuty));
        expertDto.setPictureData(multipartFile.getOriginalFilename());
        expertDto.setRegistrationDate(LocalDate.now());
        expertDto.setRegistrationTime(LocalTime.now());
        expertDto.setExpertStatus(ExpertStatus.DISABLE);
        expertDto.setScore(0);
        expertDto.setWallet(walletService.save(new Wallet(0.0)));
        return expertDto;
    }

    @Override
    public List<Expert> findAllDisableExperts() {
        return expertRepository.findAllByExpertStatus(ExpertStatus.DISABLE)
                .orElseThrow(() -> new CustomResourceNotFoundException("There is no result"));
    }
}
