package com.tutorials.flightyauthms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String GENERATE_JWT_URL = "/jwt/generate";
    public static final String EXTRACT_JWT_URL = "/jwt/extract";
    public static final String LOGIN_URL = "/login";

}
