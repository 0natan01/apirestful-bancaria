package com.example.apirestful_bancaria.controller;



import com.example.apirestful_bancaria.business.dto.ContaDTO;
import com.example.apirestful_bancaria.business.dto.DadosPessoaisDTO;
import com.example.apirestful_bancaria.business.dto.EnderecoDTO;
import com.example.apirestful_bancaria.business.dto.TelefoneDTO;
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

    @PutMapping("/dadosPessoais")
    public ResponseEntity<DadosPessoaisDTO> atualizaDadosPessoais(@RequestBody DadosPessoaisDTO dadosPessoaisDTO,
                                                          @RequestParam("id") Long id){
        return ResponseEntity.ok(contaService.alteraDadosPessoais(id , dadosPessoaisDTO));
    }

    @PutMapping("/enderecos")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO enderecoDTO ,
                                                        @RequestParam("id") Long id){
        return ResponseEntity.ok(contaService.alteraEndereco(id , enderecoDTO));
    }

    @PutMapping("/telefones")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTO telefoneDTO ,
                                                        @RequestParam("id") Long id){
        return ResponseEntity.ok(contaService.alteraTelefone(id , telefoneDTO));
    }

    @PostMapping("/endereco")
    public  ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTO enderecoDTO ,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(contaService.cadastroEndereco(token , enderecoDTO));
    }

    @PostMapping("/telefone")
    public  ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTO telefoneDTO ,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(contaService.cadastroTelefone(token , telefoneDTO));
    }

    @GetMapping
    public ResponseEntity<Conta> procuraContaPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(contaService.procuraUsuarioPorEmail(email)) ;
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        contaService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }
}
