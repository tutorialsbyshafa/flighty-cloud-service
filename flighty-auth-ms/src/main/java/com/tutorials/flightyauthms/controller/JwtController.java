package com.tutorials.flightyauthms.controller;

import static com.tutorials.flightyauthms.util.UrlConstant.EXTRACT_JWT_URL;
import static com.tutorials.flightyauthms.util.UrlConstant.GENERATE_JWT_URL;

import com.tutorials.flightyauthms.model.ExtractJwtRqModel;
import com.tutorials.flightyauthms.model.ExtractJwtRsModel;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log4j2
@Tag(name = "JWT", description = "JWT related operations")
@Validated
@RequestMapping()
public class JwtController {
    private final JwtService jwtService;

    @Operation(description = "Generate JWT using provided data")
    @PostMapping(GENERATE_JWT_URL)
    public ResponseEntity<GenerateJwtRsModel> generateToken(@Valid @RequestBody GenerateJwtRqModel requestBody) {
        log.info("Request data: [Url: {}, payload: {}]", GENERATE_JWT_URL, requestBody);

        var response = jwtService.generateToken(requestBody);

        log.info("Response data: [Url: {}, payload: {}]", GENERATE_JWT_URL, response);
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Extract payload from JWT")
    @GetMapping(EXTRACT_JWT_URL)
    public ResponseEntity<ExtractJwtRsModel> extractToken(@Valid @RequestBody ExtractJwtRqModel requestBody) {
        log.info("Request data: [Url: {}, payload: {}]", EXTRACT_JWT_URL, requestBody);

        var response = jwtService.extractToken(requestBody);

        log.info("Response data: [Url: {}, payload: {}]", EXTRACT_JWT_URL, response);
        return ResponseEntity.ok(response);
    }
}
