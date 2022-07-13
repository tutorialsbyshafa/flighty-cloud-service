package com.tutorials.flightyapigateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {
    public static final String AUTH_MS_ID = "flighty-auth-ms";
    public static final String AUTH_MS_PATH = "/auth-ms/**";
    public static final String AUTH_MS_URI = "lb://FLIGHTY-AUTH-MS";
}
