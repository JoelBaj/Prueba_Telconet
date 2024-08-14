package com.back_sistema.api.model.repository;

import com.back_sistema.api.model.entity.Residente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidenteRepository extends JpaRepository<Residente, Integer> {
    Optional<Residente> findByUsername(String username);
    Boolean existsByUsername(String username);
}
