package com.tutorials.msuser.service.impl;

import com.tutorials.msuser.client.AuthMsClient;
import com.tutorials.msuser.model.GenerateJwtRqModel;
import com.tutorials.msuser.model.GenerateJwtRsModel;
import com.tutorials.msuser.service.AuthService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthMsClient authClient;

    @Override
    public GenerateJwtRsModel generateToken(GenerateJwtRqModel requestBody) {
        var generateTokenResponse = authClient.generateToken(requestBody);
        log.info("Generate token response: {}", generateTokenResponse);
        return Objects.requireNonNull(generateTokenResponse.getBody()).getData();
    }
}