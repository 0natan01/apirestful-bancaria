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
                .id(dadosPessoais.getId())
                .rg(dadosPessoais.getRg())
                .cpf(dadosPessoais.getCpf())
                .build();

    }


    public List<EnderecoDTO> paraEnderecoListDTO(List<Endereco> endereco) {
        return endereco.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
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
                .id(telefone.getId())
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    public Conta alterarInfoConta(ContaDTO contaDTO , Conta entity){
        return Conta.builder()
                .id(entity.getId())
                .nome(contaDTO.getNome() != null ? contaDTO.getNome() : entity.getNome())
                .email(contaDTO.getEmail() != null ? contaDTO.getEmail() : entity.getEmail())
                .senha(contaDTO.getSenha() != null ? contaDTO.getSenha() : entity.getSenha())
                .telefones(entity.getTelefones())
                .dadosPessoais(entity.getDadosPessoais())
                .enderecos(entity.getEnderecos())
                .build();
    }

    public DadosPessoais alterarInfoPessoais(DadosPessoaisDTO dto , DadosPessoais entity){
        return DadosPessoais.builder()
                .id(entity.getId())
                .rg(dto.getRg() != null ? dto.getRg() : entity.getRg())
                .cpf(dto.getCpf() != null ? dto.getCpf() : entity.getCpf())
                .build();
    }

    public Endereco alterarEndereco(EnderecoDTO dto , Endereco entity){
        return Endereco.builder()
                .id(entity.getId())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
                .build();
    }

    public Telefone alterarTelefone(TelefoneDTO dto , Telefone entity){
        return Telefone.builder()
                .id(entity.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .build();
    }
}
