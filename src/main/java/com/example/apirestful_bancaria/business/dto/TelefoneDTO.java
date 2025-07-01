package com.example.apirestful_bancaria.business.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneDTO {
    private Long id;
    private String ddd;
    private String numero;
}
