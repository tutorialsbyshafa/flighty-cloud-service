package com.tutorials.msuser.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlConstant {
    public static final String SIGNUP_URL = "/signup";
    public static final String GET_BY_ID_URL = "/users/{id}";
    public static final String GENERATE_JWT_URL = "/jwt/generate";
}
