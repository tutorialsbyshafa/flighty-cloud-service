package com.tutorials.msuser.controller;

import static com.tutorials.msuser.util.UrlConstant.LOGIN_URL;
import static com.tutorials.msuser.util.UrlConstant.SIGNUP_URL;

import com.tutorials.msuser.model.LoginRequestModel;
import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;
import com.tutorials.msuser.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping()
public class UserController {
    private final UserService userService;

    @PostMapping(SIGNUP_URL)
    public ResponseEntity<SignupResponseModel> signup(@Valid @RequestBody SignupRequestModel request) {
        log.info("Request data: [Url: {}, payload: {}]", SIGNUP_URL, request);

        var response = userService.createUser(request);

        log.info("Response data: [Url: {}, payload: {}]", SIGNUP_URL, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<Object> login(@RequestBody LoginRequestModel request) {
        return ResponseEntity.ok().body(new Object());
    }
}
