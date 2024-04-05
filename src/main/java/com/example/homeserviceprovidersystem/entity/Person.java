package com.example.homeserviceprovidersystem.entity;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@Builder
public class Person extends BaseEntity<Long> {
    String firstName;
    String lastName;
    String email;
    String password;
    LocalDate RegistrationTime;
}