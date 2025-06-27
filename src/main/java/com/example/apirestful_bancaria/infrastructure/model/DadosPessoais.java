package com.example.apirestful_bancaria.infrastructure.model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dados")
@Builder
public class DadosPessoais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rg")
    private String rg;
    @Column(name = "cpf")
    private String cpf;


}
