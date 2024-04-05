package com.example.homeserviceprovidersystem.dto;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import com.example.homeserviceprovidersystem.entity.SubDuty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class DutyDto extends BaseEntity<Long> {
    String name;
    Set<SubDuty> subDuties;
}
