package com.example.apirestful_bancaria.controller;



import com.example.apirestful_bancaria.business.dto.ContaDTO;
import com.example.apirestful_bancaria.business.service.ContaService;
import com.example.apirestful_bancaria.infrastructure.model.Conta;

import com.example.apirestful_bancaria.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contabancaria")
public class ContaController  {
    private final ContaService contaService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<ContaDTO> salvarNovoUsuario(@RequestBody  ContaDTO contaDTO){
        return ResponseEntity.ok(contaService.salvarNovoUsuario(contaDTO)) ;
    }

    @PostMapping("/login")
    public String login(@RequestBody ContaDTO contaDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        contaDTO.getEmail() , contaDTO.getSenha()
                )
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @PutMapping
    public ResponseEntity<ContaDTO> atualizaDadosContaBancaria(@RequestBody ContaDTO dto ,
                                                               @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(contaService.alterarDadosUsuario(token , dto));
    }
}
