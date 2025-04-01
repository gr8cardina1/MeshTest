package com.meshgroup.meshtask.auth.service;

import com.meshgroup.meshtask.auth.model.JwtAuthentication;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class AuthService {

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getClaims(@NonNull String token) {
        return Jwts.parser()
                .setSigningKey(JwtUtils.reUseToken)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public JwtAuthentication getAuthentication() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
