package com.example.homeserviceprovidersystem.dto.duty;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DutyResponse extends BaseEntity<Long> {
    @NotBlank(message = "please enter the appropriate name")
    String name;
}