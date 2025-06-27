package com.example.apirestful_bancaria.business.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaDTO {
    private String nome;
    private String email;
    private String senha;
    private List<DadosPessoaisDTO> dadosPessoais;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefones;
}
