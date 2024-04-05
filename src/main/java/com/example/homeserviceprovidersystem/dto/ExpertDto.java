package com.example.homeserviceprovidersystem.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ExpertDto extends PersonDto {
    @Size(max = 300 * 1024, message = "Picture size must be less than or equal to 300 KB")
    @Lob
    byte[] picture;
    int score;
}
