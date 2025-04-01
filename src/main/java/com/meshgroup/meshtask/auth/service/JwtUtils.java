package com.meshgroup.meshtask.auth.service;

import com.meshgroup.meshtask.auth.model.JwtAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {
    public static Key reUseToken = JwtUtils.getSigningKey();

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
//        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setUserId(claims.get("userId", Long.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

    public static Key getSigningKey() {
        return Jwts.SIG.HS512.key().build();
    }

    public static String getNewJwtToken(Long userId){
        long jwtExpirationMs=31556952000L;
        return Jwts.builder()
                        .claim("userId",userId)
                        .issuedAt(new Date())
                        .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                        .signWith(reUseToken)
                        .compact();
    }
}
