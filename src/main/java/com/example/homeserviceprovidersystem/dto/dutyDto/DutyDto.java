package com.example.homeserviceprovidersystem.dto.dutyDto;

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
public class DutyDto extends BaseEntity<Long> {
    @NotBlank(message = "please enter the appropriate name")
    String name;
}
