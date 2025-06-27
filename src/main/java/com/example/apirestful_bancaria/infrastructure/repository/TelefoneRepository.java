package com.example.apirestful_bancaria.infrastructure.repository;


import com.example.apirestful_bancaria.infrastructure.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Long> {
}
