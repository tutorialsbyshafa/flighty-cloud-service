package com.tutorials.msuser.service;

import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.UserRsModel;
import java.util.UUID;

public interface UserService {
    UserRsModel createUser(SignupRequestModel request);

    UserRsModel getUserById(UUID id);
}
