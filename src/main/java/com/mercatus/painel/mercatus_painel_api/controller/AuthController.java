package com.mercatus.painel.mercatus_painel_api.controller;

import com.mercatus.painel.mercatus_painel_api.service.JwtTokenService;
import com.mercatus.painel.mercatus_painel_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenService jwtTokenService;

    public record LoginRequest(String email, String senha) {}
    public record LoginResponse(String token) {}

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        final UserDetails userDetails = usuarioService.loadUserByUsername(request.email());

        final String token = jwtTokenService.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));

    } // <-- PROVAVELMENTE ESTAVA FALTANDO ESTA CHAVE PARA FECHAR O MÉTODO 'login'

} // <-- E TAMBÉM ESTA CHAVE PARA FECHAR A CLASSE 'AuthController'