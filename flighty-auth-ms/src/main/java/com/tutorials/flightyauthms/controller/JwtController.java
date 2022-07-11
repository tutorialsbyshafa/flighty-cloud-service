package com.tutorials.flightyauthms.controller;

import static com.tutorials.flightyauthms.util.UrlConstant.GENERATE_JWT_URL;

import com.tutorials.flightyauthms.model.GenerateJwtRqModel;
import com.tutorials.flightyauthms.model.GenerateJwtRsModel;
import com.tutorials.flightyauthms.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log4j2
@Tag(name = "JWT", description = "JWT related operations")
@Validated
@RequestMapping("/auth-ms")
public class JwtController {
    private final JwtService jwtService;

    @Operation(description = "Generate JWT using provided data")
    @PostMapping(GENERATE_JWT_URL)
    public ResponseEntity<GenerateJwtRsModel> generateToken(@Valid @RequestBody GenerateJwtRqModel requestBody) {
        log.info("Request data: [Url: {}, payload: {}", GENERATE_JWT_URL, requestBody);

        var response = jwtService.generateToken(requestBody.getUsername(), requestBody.getRememberMe());

        log.info("Response data: [Url: {}, payload: {}", GENERATE_JWT_URL, response);
        return ResponseEntity.ok(response);
    }
}
