package com.tutorials.msbooking.controller;

import static com.tutorials.msbooking.util.Constants.BOOKINGS_URL;
import static com.tutorials.msbooking.util.Constants.REQUEST_LOG_FORMAT;
import static com.tutorials.msbooking.util.Constants.RESPONSE_LOG_FORMAT;

import com.tutorials.msbooking.model.BookingRqModel;
import com.tutorials.msbooking.model.BookingRsModel;
import com.tutorials.msbooking.model.ExtractJwtRsModel;
import com.tutorials.msbooking.service.BookingService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(BOOKINGS_URL)
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingRsModel> createFlight(Authentication auth,
                                @Valid @RequestBody BookingRqModel request) {
        log.info(REQUEST_LOG_FORMAT, BOOKINGS_URL, request);

        var username = ((ExtractJwtRsModel) auth.getPrincipal()).getUsername();
        var response = bookingService.createBooking(request, username);

        log.info(RESPONSE_LOG_FORMAT, BOOKINGS_URL, response);
        return ResponseEntity.ok().body(response);
    }
}
