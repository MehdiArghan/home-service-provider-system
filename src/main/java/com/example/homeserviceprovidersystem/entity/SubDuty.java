package com.example.homeserviceprovidersystem.entity;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class SubDuty extends BaseEntity<Long> {
    String name;
    double basePrice;
    String description;
    @ManyToOne
    @JoinColumn(name = "Duty_id",referencedColumnName = "id")
    Duty duty;
}
