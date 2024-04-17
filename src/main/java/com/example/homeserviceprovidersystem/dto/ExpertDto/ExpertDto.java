package com.example.homeserviceprovidersystem.dto.ExpertDto;

import com.example.homeserviceprovidersystem.dto.personDto.PersonDto;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.Wallet;
import com.example.homeserviceprovidersystem.entity.enums.ExpertStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ExpertDto extends PersonDto {
    @Enumerated(EnumType.STRING)
    ExpertStatus expertStatus;
    @Size(max = 300 * 1024, message = "Picture size must be less than or equal to 300 KB")
    @NotNull(message = "picture must not be null")
    @NotEmpty(message = "picture must not be empty")
    @Lob
    byte[] picture;
    int score;
    Set<SubDuty> subDuties;
    Wallet wallet;
}
