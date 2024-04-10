package com.example.homeserviceprovidersystem.entity;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Orders extends BaseEntity<Long> {
    double ProposedPrice;
    String jobDescription;
    LocalDate dateOfWork;
    LocalTime TimeOfWord;
    @Embedded
    Address address;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;
}
