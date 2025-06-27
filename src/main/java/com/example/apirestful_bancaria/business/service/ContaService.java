package com.example.apirestful_bancaria.business.service;

import com.example.apirestful_bancaria.business.converter.ContaConverter;
import com.example.apirestful_bancaria.business.dto.ContaDTO;
import com.example.apirestful_bancaria.infrastructure.model.Conta;
import com.example.apirestful_bancaria.infrastructure.repository.ContaRespository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRespository contaRespository;
    private final PasswordEncoder passwordEncoder;
    private final ContaConverter contaConverter;

    public ContaDTO salvarNovoUsuario(ContaDTO contaDTO){
        contaDTO.setSenha(passwordEncoder.encode(contaDTO.getSenha()));
        Conta conta = contaConverter.paraConta(contaDTO);
        conta = contaRespository.save(conta);
        return contaConverter.paraContaDTO(conta);

    }
}
