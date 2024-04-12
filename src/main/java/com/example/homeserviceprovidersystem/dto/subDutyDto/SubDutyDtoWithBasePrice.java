package com.example.homeserviceprovidersystem.dto.subDutyDto;

import com.example.homeserviceprovidersystem.base.BaseEntity;
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
public class SubDutyDtoWithBasePrice extends BaseEntity<Long> {
    @NotNull(message = "please enter the appropriate price")
    @Positive(message = "please enter a positive price")
    double basePrice;
}
