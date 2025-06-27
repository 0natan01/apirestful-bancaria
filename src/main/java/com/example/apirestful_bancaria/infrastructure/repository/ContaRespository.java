package com.example.apirestful_bancaria.infrastructure.repository;


import com.example.apirestful_bancaria.infrastructure.model.Conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ContaRespository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByEmail(String email);
}
