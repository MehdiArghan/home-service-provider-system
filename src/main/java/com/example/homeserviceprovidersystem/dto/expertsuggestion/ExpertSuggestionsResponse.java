package com.example.homeserviceprovidersystem.dto.expertsuggestion;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ExpertSuggestionsResponse extends BaseEntity<Long> {
    @FutureOrPresent(message = "Date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter the appropriate date")
    LocalDate offerDate;
    @FutureOrPresent(message = "time must be in the present or future")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Please enter an appropriate time")
    LocalTime offerTime;
    @NotNull(message = "value is null")
    @Positive(message = "value proposedPrice must be positive")
    double ProposedPrice;
    @FutureOrPresent(message = "Date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter the appropriate date")
    LocalDate DateOfStartWork;
    @FutureOrPresent(message = "time must be in the present or future")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Please enter an appropriate time")
    LocalTime TimeOfStartWork;
    @NotNull(message = "value is null")
    @Positive(message = "value proposedPrice must be positive")
    Integer durationOfWorkPerHour;
    @NotBlank(message = "please enter an appropriate nameSubDuty")
    @Pattern(regexp = "[a-zA-Z]+", message = "nameSubDuty must contain only letters")
    String nameSubDuty;
}
