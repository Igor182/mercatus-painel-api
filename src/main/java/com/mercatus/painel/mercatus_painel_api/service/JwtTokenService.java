// Garanta que esta linha de 'package' está correta para a sua estrutura
package com.mercatus.painel.mercatus_painel_api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {

    // CHAVE SECRETA para assinar o token. Em produção, isso deve vir de um arquivo de configuração!
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationTime = 3600000; // 1 hora em milissegundos

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Você pode adicionar informações extras (claims) ao token aqui se precisar

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername()) // O "dono" do token (no nosso caso, o e-mail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}