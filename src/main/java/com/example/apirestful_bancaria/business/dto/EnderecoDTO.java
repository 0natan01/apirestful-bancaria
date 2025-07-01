package com.example.apirestful_bancaria.business.dto;


import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTO {
    private Long id;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;

}
