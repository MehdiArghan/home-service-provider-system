package com.example.homeserviceprovidersystem.dto.ordersDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AddressDto {
    @NotBlank(message = "Please enter the name of your province")
    String province;
    @NotBlank(message = "Please enter the name of your city")
    String city;
    @NotBlank(message = "Please enter the name of your street")
    String street;
    @NotBlank(message = "Please enter your postalCode")
    String postalCode;
}