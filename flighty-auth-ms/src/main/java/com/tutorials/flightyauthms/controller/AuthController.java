package com.tutorials.flightyauthms.controller;

import static com.tutorials.flightyauthms.util.UrlConstant.GENERATE_JWT_URL;
import static com.tutorials.flightyauthms.util.UrlConstant.LOGIN_URL;

import com.tutorials.flightyauthms.model.LoginRequestModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "AUTH", description = "AUTH related operations")
@Validated
@RequestMapping()
public class AuthController {

    @Operation(description = "Login by username and password")
    @PostMapping(LOGIN_URL)
    public ResponseEntity<Object> login(@RequestBody LoginRequestModel request) {
        log.info("Request data: [Url: {}, payload: {}]", GENERATE_JWT_URL, request);
        return ResponseEntity.ok().body(new Object());
    }

}
