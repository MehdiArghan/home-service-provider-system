package com.example.homeserviceprovidersystem.dto.subduty;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import com.example.homeserviceprovidersystem.entity.Duty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubDutyResponse extends BaseEntity<Long> {
    @NotBlank(message = "please enter an appropriate name")
    @Pattern(regexp = "[a-zA-Z]+", message = "name must contain only letters")
    String name;
    @NotNull(message = "please enter an appropriate price")
    @Positive(message = "please enter a positive price")
    double basePrice;
    @NotBlank(message = "please enter an appropriate description")
    @Pattern(regexp = "[a-zA-Z]+", message = "description must contain only letters")
    String description;
    Duty duty;
}
