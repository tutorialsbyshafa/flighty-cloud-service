package com.tutorials.flightyapigateway.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {
    public static final String AUTH_MS_ID = "flighty-auth-ms";
    public static final String MS_USER_ID = "ms-user";
    public static final String MS_FLIGHT_ID = "ms-flight";

    public static final String AUTH_MS_PATH = "/auth-ms/**";
    public static final String MS_USER_PATH = "/ms-user/**";
    public static final String MS_FLIGHT_PATH = "/ms-flight/**";

    public static final String AUTH_MS_URI = "lb://FLIGHTY-AUTH-MS";
    public static final String MS_USER_URI = "lb://MS-USER";
    public static final String MS_FLIGHT_URI = "lb://MS-FLIGHT";
}
