package com.example.homeserviceprovidersystem.dto.subDutyDto;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubDutyDtoWithDescription extends BaseEntity<Long> {
    @NotBlank(message = "please enter the appropriate description")
    String description;
}
