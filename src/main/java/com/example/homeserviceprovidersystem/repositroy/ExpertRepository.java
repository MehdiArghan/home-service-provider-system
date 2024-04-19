package com.example.homeserviceprovidersystem.repositroy;

import com.example.homeserviceprovidersystem.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {
}
