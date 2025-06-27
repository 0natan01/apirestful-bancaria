package com.example.apirestful_bancaria.infrastructure.repository;


import com.example.apirestful_bancaria.infrastructure.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
