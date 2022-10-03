package com.manhpd.ssecurityjwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtils implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return this.doGenerateToken(claims, userDetails.getUsername());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String userName = this.getUsernameFromToken(token);
        return userName.equals(userDetails.getUsername())
                                                && !isTokenExpired(token);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
     * 2. Sign the JWT using the HS512 algorithm and secret key.
     * 3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1) to a URL-safe string
     *
     * @param claims
     * @param subject
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(subject)
                   .setIssuedAt(new Date())
                   .setExpiration(this.genExpirationDate())
//                   .signWith(SignatureAlgorithm.HS512, this.secret)
                   .compact();
    }

    private Date genExpirationDate() {
        return new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);
    }

}
