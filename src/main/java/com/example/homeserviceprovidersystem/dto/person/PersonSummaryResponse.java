package com.example.homeserviceprovidersystem.dto.person;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class PersonSummaryResponse extends BaseEntity<Long> {
    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be empty")
    @Column(nullable = false)
    String firstName;
    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName cannot be empty")
    @Column(nullable = false)
    String lastName;
    @NotNull(message = "email cannot be null")
    @Email(message = "please enter an appropriate Email")
    @Column(unique = true)
    String email;
    @PastOrPresent(message = "date should not be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter the appropriate date")
    LocalDate registrationDate;
    @PastOrPresent(message = "Time should not be in the past")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Please enter an appropriate time")
    LocalTime registrationTime;
}
