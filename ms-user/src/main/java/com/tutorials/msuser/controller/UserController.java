package com.tutorials.msuser.controller;

import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;
import com.tutorials.msuser.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/ms-user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseModel> signup(@Valid @RequestBody SignupRequestModel request) {
        var response = userService.signup(request);
        return ResponseEntity.ok(response);
    }
}
