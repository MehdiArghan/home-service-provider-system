package com.example.homeserviceprovidersystem.entity.person;

import com.example.homeserviceprovidersystem.entity.person.enums.ExpertStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Expert extends Person {
    @Enumerated(EnumType.STRING)
    ExpertStatus expertStatus;
    @Lob
    byte[] picture;
}
