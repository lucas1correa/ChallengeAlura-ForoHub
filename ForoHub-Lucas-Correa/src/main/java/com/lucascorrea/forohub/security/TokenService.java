package com.lucascorrea.forohub.security;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Service
public class TokenService {
    @Value("${api.security.secret:my-very-secret-key-12345678901234567890}")
    private String secret;
    private long expirationMs = 2*60*60*1000;
    public String generarToken(String subject){
        Date ahora=new Date(); Date exp=new Date(ahora.getTime()+expirationMs);
        return Jwts.builder().setIssuer("ForoHub Lucas Correa").setSubject(subject).setIssuedAt(ahora).setExpiration(exp)
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256).compact();
    }
    public String getSubject(String token){
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
    }
}