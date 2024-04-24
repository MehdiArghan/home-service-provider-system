package com.example.homeserviceprovidersystem.dto.ordersDto;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import com.example.homeserviceprovidersystem.entity.Address;
import com.example.homeserviceprovidersystem.entity.Customer;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class OrdersDto extends BaseEntity<Long> {
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
    Address address;
    OrderStatus orderStatus;
    Customer customer;
    Expert expert;
    SubDuty subDuty;
}
