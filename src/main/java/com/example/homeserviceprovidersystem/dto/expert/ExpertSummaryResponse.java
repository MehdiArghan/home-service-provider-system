package com.example.homeserviceprovidersystem.dto.expert;

import com.example.homeserviceprovidersystem.dto.person.PersonSummaryResponse;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.enums.ExpertStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ExpertSummaryResponse extends PersonSummaryResponse {
    @Enumerated(EnumType.STRING)
    ExpertStatus expertStatus;
    @NotNull(message = "picture must not be null")
    @NotEmpty(message = "picture must not be empty")
    String pictureData;
    @Pattern(regexp = "[-+]?[0-9]+", message = "score must contain any int, positive or negative")
    int score;
    Set<SubDuty> subDuties;
}
