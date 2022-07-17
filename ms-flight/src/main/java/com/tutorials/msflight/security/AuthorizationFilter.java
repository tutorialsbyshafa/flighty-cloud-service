package com.tutorials.msflight.security;


import com.tutorials.msflight.service.AuthService;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        authorizeUser(request, response, chain);

    }

    private void authorizeUser(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        var authorization = Optional.ofNullable(req.getHeader("Authorization"));

        authorization.ifPresent(auth -> {
            var userData = authService.extractToken(auth);

            var grantedAuthorities =
                    userData.getRoles()
                            .stream()
                            .map(this::getGrantedAuthority)
                            .collect(Collectors.toList());

            var authToken = new UsernamePasswordAuthenticationToken(userData, null, grantedAuthorities);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        });

        chain.doFilter(req, res);
    }

    private GrantedAuthority getGrantedAuthority(String r) {
        return () -> r;
    }
}
