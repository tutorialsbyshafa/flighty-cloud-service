package com.tutorials.msuser.controller;

import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;
import com.tutorials.msuser.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/ms-user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseModel> signup(@Valid @RequestBody SignupRequestModel request) {
        log.info("Request data: [Url: {}, payload: {}]", "/signup", request);

        var response = userService.signup(request);

        log.info("Response data: [Url: {}, payload: {}]", "/signup", response);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
