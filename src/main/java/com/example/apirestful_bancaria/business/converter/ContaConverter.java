package com.example.apirestful_bancaria.business.converter;

import com.example.apirestful_bancaria.business.dto.ContaDTO;
import com.example.apirestful_bancaria.business.dto.DadosPessoaisDTO;
import com.example.apirestful_bancaria.business.dto.EnderecoDTO;
import com.example.apirestful_bancaria.business.dto.TelefoneDTO;
import com.example.apirestful_bancaria.infrastructure.model.Conta;
import com.example.apirestful_bancaria.infrastructure.model.DadosPessoais;
import com.example.apirestful_bancaria.infrastructure.model.Endereco;
import com.example.apirestful_bancaria.infrastructure.model.Telefone;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ContaConverter {
    public Conta paraConta(ContaDTO contaDTO){
        return Conta.builder()
                .nome(contaDTO.getNome())
                .email(contaDTO.getEmail())
                .senha(contaDTO.getSenha())
                .dadosPessoais(paraDadosPessoaisList(contaDTO.getDadosPessoais()))
                .enderecos(paraEnderecoList(contaDTO.getEnderecos()))
                .telefones(paraTelefoneList(contaDTO.getTelefones()))
                .build();
    }

    public List<DadosPessoais> paraDadosPessoaisList(List<DadosPessoaisDTO> dadosPessoaisDTO){
        return dadosPessoaisDTO.stream().map(this::paraDadosPessoais).toList();
    }

    public DadosPessoais paraDadosPessoais(DadosPessoaisDTO dadosPessoaisDTO){
        return DadosPessoais.builder()
                .rg(dadosPessoaisDTO.getRg())
                .cpf(dadosPessoaisDTO.getCpf())
                .build();

    }


    public List<Endereco> paraEnderecoList(List<EnderecoDTO> enderecoDTOS) {
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraTelefoneList(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }


    public ContaDTO paraContaDTO(Conta conta){
        return ContaDTO.builder()
                .nome(conta.getNome())
                .email(conta.getEmail())
                .senha(conta.getSenha())
                .dadosPessoais(paraDadosPessoaisListDTO(conta.getDadosPessoais()))
                .enderecos(paraEnderecoListDTO(conta.getEnderecos()))
                .telefones(paraTelefoneListDTO(conta.getTelefones()))
                .build();
    }

    public List<DadosPessoaisDTO> paraDadosPessoaisListDTO(List<DadosPessoais> dadosPessoais){
        return dadosPessoais.stream().map(this::paraDadosPessoaisDTO).toList();
    }

    public DadosPessoaisDTO paraDadosPessoaisDTO(DadosPessoais dadosPessoais){
        return DadosPessoaisDTO.builder()
                .rg(dadosPessoais.getRg())
                .cpf(dadosPessoais.getCpf())
                .build();

    }


    public List<EnderecoDTO> paraEnderecoListDTO(List<Endereco> endereco) {
        return endereco.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraTelefoneListDTO(List<Telefone> telefone){
        return telefone.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }
}
