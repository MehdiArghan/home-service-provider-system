package com.example.homeserviceprovidersystem.dto.subDutyDto;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import com.example.homeserviceprovidersystem.entity.Duty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubDutyDto extends BaseEntity<Long> {
    @NotBlank(message = "please enter the appropriate name")
    String name;
    @NotNull(message = "please enter the appropriate price")
    @Positive(message = "please enter a positive price")
    double basePrice;
    @NotBlank(message = "please enter the appropriate description")
    String description;
    Duty duty;
}
