package com.niall.bankserver.jwt;

import com.niall.bankserver.dtos.AccountDto;
import com.niall.bankserver.services.AccountService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Autowired
    private JwtParser jwtParser;
    @Autowired
    private AccountService accountService;
    @Value("${jwt.token.expiration.minutes}")
    private int tokenExpirationMinutes;

    private Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String createBearerToken(String accountNumber) {
        Claims claims = Jwts.claims().setSubject(accountNumber);
        return createJwtToken(tokenExpirationMinutes, claims);
    }

    public Authentication getAuthentication(String token) {
        int accountNumber = Integer.parseInt(jwtParser.getAccountNumber(token));
        AccountDto account = accountService.getAccountByAccountNumber(accountNumber);
        return new UsernamePasswordAuthenticationToken(account, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + account.getAccountType().name())));
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = jwtParser.getClaims(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Invalid JWT token");
            return false;
        }
    }

    private String createJwtToken(int tokenExpirationMinutes, Claims claims) {
        long tokenExpirationMilliseconds = tokenExpirationMinutes * 60 * 1000;
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenExpirationMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtParser.getSecret())
                .compact();
    }
}
