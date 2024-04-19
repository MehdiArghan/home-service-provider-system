package com.example.homeserviceprovidersystem.dto.personDto;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.persistence.Column;
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
public class PersonDto extends BaseEntity<Long> {
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
    @NotBlank(message = "please enter the appropriate password")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
            message = "Password must be at least 8 characters long and contain at least one letter and one number")
    String password;
    @PastOrPresent(message = "date should not be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter the appropriate date")
    LocalDate registrationDate;
    @PastOrPresent(message = "Time should not be in the past")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @NotNull(message = "Please enter the appropriate time")
    LocalTime registrationTime;

    public PersonDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
