package com.example.homeserviceprovidersystem.entity;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Duty extends BaseEntity<Long> {
    String name;
    @OneToMany(mappedBy = "duty", cascade = CascadeType.REMOVE)
    Set<SubDuty> subDuties;
}
