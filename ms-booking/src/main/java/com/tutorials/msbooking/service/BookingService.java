package com.tutorials.msbooking.service;

import com.tutorials.msbooking.model.BookingRqModel;
import com.tutorials.msbooking.model.BookingRsModel;

public interface BookingService {
    BookingRsModel createBooking(BookingRqModel request, String username);
}
