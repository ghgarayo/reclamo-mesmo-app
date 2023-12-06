package reclamo.mesmo.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reclamo.mesmo.app.domain.usuario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Reclamo Mesmo")
                    .withSubject(usuario.getLogin())
                    .withClaim("isAdmin", usuario.isAdmin())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Reclamo Mesmo")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTCreationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }


    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
