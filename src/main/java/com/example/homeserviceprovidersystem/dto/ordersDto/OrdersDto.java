package com.example.homeserviceprovidersystem.dto.ordersDto;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import com.example.homeserviceprovidersystem.entity.Address;
import com.example.homeserviceprovidersystem.entity.Customer;
import com.example.homeserviceprovidersystem.entity.Expert;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class OrdersDto extends BaseEntity<Long> {
    double ProposedPrice;
    String jobDescription;
    LocalDate dateOfWork;
    LocalTime TimeOfWord;
    @Embedded
    Address address;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;
    Customer customer;
    Expert expert;
    SubDuty subDuty;
}
