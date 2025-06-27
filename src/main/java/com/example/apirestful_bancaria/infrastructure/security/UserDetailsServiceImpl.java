package com.example.apirestful_bancaria.infrastructure.security;

import com.example.apirestful_bancaria.infrastructure.model.Conta;
import com.example.apirestful_bancaria.infrastructure.repository.ContaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Repositório para acessar dados de usuário no banco de dados
    @Autowired
    private ContaRespository contaRespository;

    // Implementação do método para carregar detalhes do usuário pelo e-mail
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo e-mail
        Conta conta = contaRespository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(conta.getEmail()) // Define o nome de usuário como o e-mail
                .password(conta.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}