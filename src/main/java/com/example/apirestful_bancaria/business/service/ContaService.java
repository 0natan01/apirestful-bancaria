package com.example.apirestful_bancaria.business.service;

import com.example.apirestful_bancaria.business.converter.ContaConverter;
import com.example.apirestful_bancaria.business.dto.ContaDTO;
import com.example.apirestful_bancaria.infrastructure.exceptions.ResourceNotFoundExceptions;
import com.example.apirestful_bancaria.infrastructure.model.Conta;
import com.example.apirestful_bancaria.infrastructure.repository.ContaRespository;
import com.example.apirestful_bancaria.infrastructure.security.JwtUtil;
import io.jsonwebtoken.Jwe;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRespository contaRespository;
    private final PasswordEncoder passwordEncoder;
    private final ContaConverter contaConverter;
    private final JwtUtil jwtUtil;

    public ContaDTO salvarNovoUsuario(ContaDTO contaDTO){
        contaDTO.setSenha(passwordEncoder.encode(contaDTO.getSenha()));
        Conta conta = contaConverter.paraConta(contaDTO);
        conta = contaRespository.save(conta);
        return contaConverter.paraContaDTO(conta);

    }

    public ContaDTO alterarDadosUsuario(String token,  ContaDTO dto ){
        String email = jwtUtil.extraiEmail(token.substring(7));

        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

        Conta entityConta = contaRespository.findByEmail(email).orElseThrow(() ->

                new ResourceNotFoundExceptions("Email nao localizado"));
        Conta conta = contaConverter.alterarInfoConta(dto , entityConta);

        return contaConverter.paraContaDTO(contaRespository.save(conta));
    }
}
