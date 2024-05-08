package com.example.homeserviceprovidersystem.dto.subduty;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubDutyRequestWithDescription {
    @NotBlank(message = "please enter the appropriate nameSubDuty")
    String nameSubDuty;
    @NotBlank(message = "please enter the appropriate description")
    String description;
}
