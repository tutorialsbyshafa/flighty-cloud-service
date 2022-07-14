package com.tutorials.msuser.service;

import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.SignupResponseModel;

public interface UserService {
    SignupResponseModel signup(SignupRequestModel request);
}
