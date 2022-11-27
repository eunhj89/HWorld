package com.uno.hworld.common;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("{spring.jwt.secret}")
    private String secretKey; //parameter store will be used for this

    //private long tokenValidMilisecond = 1000L * 60 * 60; // 1 hour effective

    public String generateToken(String id) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        return Jwts.builder()
                .setId(id)
                .setIssuedAt(Timestamp.valueOf(now))
                .setExpiration(Timestamp.valueOf(now.plusHours(1)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserNameFromJwt(String jwt) {
        return getClaims(jwt).getBody().getId();
    }

    public boolean validateToken(String jwt) {
        return getClaims(jwt) != null;
    }

    public Jws<Claims> getClaims(String jwt) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
        } catch (SignatureException se) {
            log.error("Invalid JWT signature");
            throw se;
        } catch (MalformedJwtException me) {
            log.error("Invalid JWT token");
            throw me;
        } catch (ExpiredJwtException ee) {
            log.error("Expired JWT token");
            throw ee;
        } catch (UnsupportedJwtException ue) {
            log.error("Unsupported JWT token");
            throw ue;
        } catch (IllegalArgumentException ie) {
            log.error("JWT claims string is empty");
            throw ie;
        }
    }
}
