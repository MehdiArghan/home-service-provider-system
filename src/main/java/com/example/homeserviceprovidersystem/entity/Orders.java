package com.example.homeserviceprovidersystem.entity;

import com.example.homeserviceprovidersystem.base.BaseEntity;
import com.example.homeserviceprovidersystem.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Orders extends BaseEntity<Long> {
    double ProposedPrice;
    String jobDescription;
    LocalDate dateOfWork;
    LocalTime TimeOfWord;
    @Embedded
    Address address;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    Customer customer;
    @ManyToOne
    @JoinColumn(name = "Expert_id", referencedColumnName = "id")
    Expert expert;
}
