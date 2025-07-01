package com.example.apirestful_bancaria.business.service;

import com.example.apirestful_bancaria.business.converter.ContaConverter;
import com.example.apirestful_bancaria.business.dto.ContaDTO;
import com.example.apirestful_bancaria.business.dto.DadosPessoaisDTO;
import com.example.apirestful_bancaria.business.dto.EnderecoDTO;
import com.example.apirestful_bancaria.business.dto.TelefoneDTO;
import com.example.apirestful_bancaria.infrastructure.exceptions.ResourceNotFoundExceptions;
import com.example.apirestful_bancaria.infrastructure.model.Conta;
import com.example.apirestful_bancaria.infrastructure.model.DadosPessoais;
import com.example.apirestful_bancaria.infrastructure.model.Endereco;
import com.example.apirestful_bancaria.infrastructure.model.Telefone;
import com.example.apirestful_bancaria.infrastructure.repository.ContaRespository;
import com.example.apirestful_bancaria.infrastructure.repository.DadosPessoaisRepository;
import com.example.apirestful_bancaria.infrastructure.repository.EnderecoRepository;
import com.example.apirestful_bancaria.infrastructure.repository.TelefoneRepository;
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
    private final DadosPessoaisRepository dadosPessoaisRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

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

    public DadosPessoaisDTO alteraDadosPessoais(Long idDadosPessoais , DadosPessoaisDTO dadosPessoaisDTO){
        DadosPessoais entity = dadosPessoaisRepository.findById(idDadosPessoais).orElseThrow(() ->
                new ResourceNotFoundExceptions("Id invalido!" + idDadosPessoais));

        DadosPessoais dadosPessoais = contaConverter.alterarInfoPessoais(dadosPessoaisDTO , entity);

        return contaConverter.paraDadosPessoaisDTO(dadosPessoaisRepository.save(dadosPessoais));
    }


    public EnderecoDTO alteraEndereco(Long idEndereco , EnderecoDTO enderecoDTO){
        Endereco entity = enderecoRepository.findById(idEndereco).orElseThrow(() ->
                new ResourceNotFoundExceptions("Id inválido!" + idEndereco));

        Endereco endereco = contaConverter.alterarEndereco(enderecoDTO , entity);

        return contaConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
    }

    public TelefoneDTO alteraTelefone(Long idTelefone , TelefoneDTO telefoneDTO){
        Telefone entity = telefoneRepository.findById(idTelefone).orElseThrow(() ->
                new ResourceNotFoundExceptions("Id inválido!" + idTelefone));

        Telefone telefone = contaConverter.alterarTelefone(telefoneDTO , entity);

        return contaConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
   }
}
