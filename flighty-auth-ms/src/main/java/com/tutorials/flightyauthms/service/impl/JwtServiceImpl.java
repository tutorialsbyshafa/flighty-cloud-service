package com.tutorials.flightyauthms.service.impl;


import com.tutorials.flightyauthms.model.GenerateJwtRsModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class JwtServiceImpl implements com.tutorials.flightyauthms.service.JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry.default}")
    private long expiryDefault;

    @Value("${jwt.expiry.remember}")
    private long expiryRememberMe;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.prefix}")
    private String prefix;

    @Override
    public GenerateJwtRsModel generateToken(String username, boolean rememberMe) {
        final Date now = new Date();
        final long delta = rememberMe ? expiryRememberMe : expiryDefault;

        var token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + delta))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return GenerateJwtRsModel.builder().token(String.format("%s %s", prefix, token)).build();
    }

    @Override
    public Optional<String> extractSubjectFromToken(HttpServletRequest request) {
        return getTokenFromRequest(request)
                .flatMap(this::parseTokenToClaims)
                .map(this::getSubjectFromClaims);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(header))
                .filter(h -> h.startsWith(prefix))
                .map(h -> h.substring(prefix.length()));
    }


    private Optional<Jws<Claims>> parseTokenToClaims(String token) {
        return Optional.of(Jwts.parser().setSigningKey(secret).parseClaimsJws(token));
    }

    private String getSubjectFromClaims(Jws<Claims> claimsJws) {
        return claimsJws.getBody().getSubject();
    }

}
