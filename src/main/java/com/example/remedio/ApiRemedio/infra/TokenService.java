package com.example.remedio.ApiRemedio.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.remedio.ApiRemedio.usuarios.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("remedio_api").withSubject(usuario.getLogin())
					.withExpiresAt(dataExperiacao()).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error ao gerar token", exception);
		}
	}

	private Instant dataExperiacao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
