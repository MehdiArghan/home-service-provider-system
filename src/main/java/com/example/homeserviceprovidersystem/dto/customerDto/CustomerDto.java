package com.example.homeserviceprovidersystem.dto.customerDto;

import com.example.homeserviceprovidersystem.dto.personDto.PersonDto;
import com.example.homeserviceprovidersystem.entity.Wallet;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CustomerDto extends PersonDto {
    Wallet wallet;
}
