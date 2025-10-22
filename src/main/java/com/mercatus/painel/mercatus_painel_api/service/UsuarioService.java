package com.mercatus.painel.mercatus_painel_api.service;

import com.mercatus.painel.mercatus_painel_api.model.Usuario; // Ajuste o import se necessário
import com.mercatus.painel.mercatus_painel_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User; // <-- Importação do Spring Security
import org.springframework.security.core.userdetails.UserDetails; // <-- Importação do Spring Security
import org.springframework.security.core.userdetails.UserDetailsService; // <-- Importação do Spring Security
import org.springframework.security.core.userdetails.UsernameNotFoundException; // <-- Importação do Spring Security
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList; // Importe o java.util.ArrayList

@Service
// 1. FAZEMOS O SERVICE IMPLEMENTAR A INTERFACE DO SPRING SECURITY
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    // 2. IMPLEMENTAMOS O MÉTODO EXIGIDO PELA INTERFACE
    /**
     * Este método é o que o Spring Security usa para carregar um usuário
     * pelo "username" (que no nosso caso, é o e-mail).
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo e-mail
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        // Cria e retorna um objeto UserDetails que o Spring Security entende.
        // Nós passamos o email, a *senha criptografada* do banco, e uma lista vazia de permissões.
        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                new ArrayList<>() // Futuramente, aqui ficarão as "Roles" (ex: "ROLE_ADMIN")
        );
    }
}