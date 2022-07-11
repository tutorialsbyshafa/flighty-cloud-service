package com.tutorials.flightyauthms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.flightyauthms.model.ErrorResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        if (!"token".equals(req.getHeader("X-Gateway-Token"))) {
            handleExternalRequest(res);
        } else {
            chain.doFilter(req, res);
        }
    }

    private void handleExternalRequest(HttpServletResponse res) throws IOException {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType("application/json");
        objectMapper.writeValue(
                res.getOutputStream(),
                ErrorResponseModel.builder()
                        .code(9999)
                        .message("Unauthorized request!")
                        .build());

        logger.error("Unauthorized request!");
    }

}
