package com.tutorials.msuser.service;

import com.tutorials.msuser.model.GenerateJwtRqModel;
import com.tutorials.msuser.model.GenerateJwtRsModel;

public interface AuthService {

    GenerateJwtRsModel generateToken(GenerateJwtRqModel requestBody);
}
