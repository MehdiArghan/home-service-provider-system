package com.example.homeserviceprovidersystem.dto;

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
@Builder
public class DutyDtoWithName extends BaseEntity<Long> {
    @NotBlank
    String name;
}
