package com.tutorials.flightyauthms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.flightyauthms.model.ErrorResponseModel;
import com.tutorials.flightyauthms.model.ResponseModel;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        if (Objects.isNull(req.getHeader("X-Gateway-Token"))
                || !req.getHeader("X-Gateway-Token").equals("token")) {
            handleExternalRequest(res);
        }

        chain.doFilter(req, res);
    }

    private void handleExternalRequest(HttpServletResponse res) throws IOException {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType("application/json");
        objectMapper.writeValue(
                res.getOutputStream(),
                ResponseModel.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ErrorResponseModel.builder()
                                .code(9999)
                                .message("Unauthorized request!")
                                .build())
                        .build());

        logger.error("Unauthorized request!");
    }

}
