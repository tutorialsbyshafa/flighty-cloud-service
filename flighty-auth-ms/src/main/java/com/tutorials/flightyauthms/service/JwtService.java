package com.tutorials.flightyauthms.service;

import com.tutorials.flightyauthms.model.GenerateJwtRsModel;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

public interface JwtService {
    GenerateJwtRsModel generateToken(String username, boolean rememberMe);

    Optional<String> extractSubjectFromToken(HttpServletRequest request);
}
