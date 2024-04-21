package com.example.homeserviceprovidersystem.dto.ExpertDto;

import com.example.homeserviceprovidersystem.dto.personDto.PersonDto;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import com.example.homeserviceprovidersystem.entity.Wallet;
import com.example.homeserviceprovidersystem.entity.enums.ExpertStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "picture must not be null")
    @NotEmpty(message = "picture must not be empty")
    String pictureData;
    int score;
    Set<SubDuty> subDuties;
    Wallet wallet;

    public ExpertDto(
            @NotNull(message = "firstName cannot be null") @NotEmpty(message = "firstName cannot be empty") String firstName,
            @NotNull(message = "lastName cannot be null") @NotEmpty(message = "lastName cannot be empty") String lastName,
            @NotNull(message = "email cannot be null") @Email(message = "please enter the appropriate Email") String email,
            @NotBlank(message = "please enter the appropriate password") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
                    message = "Password must be at least 8 characters long and contain at least one letter and one number") String password) {
        super(firstName, lastName, email, password);
    }
}
