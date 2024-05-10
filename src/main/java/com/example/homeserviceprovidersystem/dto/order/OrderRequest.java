package com.example.homeserviceprovidersystem.dto.order;

import com.example.homeserviceprovidersystem.dto.address.AddressRequest;
import jakarta.validation.Valid;
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
public class OrderRequest {
    @NotBlank(message = "please enter an appropriate nameSubDuty")
    String subDutyName;
    @NotNull(message = "email cannot be null")
    @Email(message = "please enter an appropriate Email")
    String customerEmail;
    @NotNull(message = "value is null")
    @Positive(message = "value proposedPrice must be positive")
    double ProposedPrice;
    @NotBlank(message = "please write a suitable description for the job")
    String jobDescription;
    @FutureOrPresent(message = "Date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter the appropriate date")
    LocalDate dateOfWork;
    @FutureOrPresent(message = "time must be in the present or future")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Please enter an appropriate time")
    LocalTime TimeOfWord;
    @Valid
    AddressRequest address;
}
