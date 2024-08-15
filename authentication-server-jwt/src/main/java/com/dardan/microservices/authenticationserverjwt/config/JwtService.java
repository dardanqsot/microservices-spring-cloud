package com.dardan.microservices.authenticationserverjwt.config;

import com.dardan.microservices.authenticationserverjwt.model.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${darwin.security.key:dardan}")
    private String dardanKey;

    public String generateToken(UserDetails userDetails) {
        UserEntity entity = (UserEntity) userDetails;
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("email", entity.getEmail());
        extraClaims.put("name", entity.getName());
        extraClaims.put("authorities", entity.getRoles());


        return Jwts.builder()
                .addClaims(extraClaims)
                .setSubject(entity.getUsername())
                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + (15 * 1000)))
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(dardanKey.getBytes()));
    }

}
