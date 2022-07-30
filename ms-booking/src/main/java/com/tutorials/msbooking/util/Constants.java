package com.tutorials.msbooking.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class Constants {
    public static final String REQUEST_LOG_FORMAT = "Request data: [URL: {}, payload: {}]";
    public static final String RESPONSE_LOG_FORMAT = "Response data: [URL:{}, payload: {}]";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String BOOKINGS_URL = "/bookings";
}
