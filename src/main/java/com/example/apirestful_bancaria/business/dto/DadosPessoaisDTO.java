package com.example.apirestful_bancaria.business.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosPessoaisDTO {
    private Long id;
    private String rg;
    private String cpf;
}
