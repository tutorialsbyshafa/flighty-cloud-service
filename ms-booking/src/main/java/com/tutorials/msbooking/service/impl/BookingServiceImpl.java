package com.tutorials.msbooking.service.impl;

import static com.tutorials.msbooking.mapper.BookingMapper.BOOKING_MAPPER_INSTANCE;

import com.tutorials.msbooking.entity.Booking;
import com.tutorials.msbooking.entity.User;
import com.tutorials.msbooking.exception.FlightException;
import com.tutorials.msbooking.model.BookingRqModel;
import com.tutorials.msbooking.model.BookingRsModel;
import com.tutorials.msbooking.repository.BookingRepository;
import com.tutorials.msbooking.service.BookingService;
import com.tutorials.msbooking.service.FlightService;
import com.tutorials.msbooking.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepo;
    private final FlightService flightService;
    private final UserService userService;

    @Override
    public BookingRsModel createBooking(BookingRqModel request, String username) {
        var flight = flightService.flightById(request.getFlightId());
        var user = userService.userByUsername(username);

        var booking = BOOKING_MAPPER_INSTANCE.mapRequestToEntity(request);
        booking.setUser(user);
        booking.setFlight(flight);
        bookingRepo.save(booking);

        log.info("Booking saved: {}", booking);
        return BOOKING_MAPPER_INSTANCE.mapEntityToResponse(booking);
    }

    @Override
    public BookingRsModel updateBooking(BookingRqModel request, UUID id, String username) {
        var booking = bookingByIdAndUser(id, userService.userByUsername(username));
        var flight = flightService.flightById(request.getFlightId());
        booking.setFlight(flight);
        bookingRepo.save(booking);

        log.info("Booking updated: {}", booking);
        return BOOKING_MAPPER_INSTANCE.mapEntityToResponse(booking);
    }

    @Override
    public List<BookingRsModel> getAllBookings(String username) {
        var bookings = bookingRepo.findAllByUserAndActiveTrue(userService.userByUsername(username));

        return bookings
                .stream()
                .map(BOOKING_MAPPER_INSTANCE::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingRsModel getBookingByIdAndUser(UUID id, String username) {
        var booking = bookingByIdAndUser(id, userService.userByUsername(username));
        return BOOKING_MAPPER_INSTANCE.mapEntityToResponse(booking);
    }

    @Override
    public BookingRsModel deleteBooking(UUID id, String username) {
        var booking = bookingByIdAndUser(id, userService.userByUsername(username));
        booking.setActive(false);
        bookingRepo.save(booking);

        log.info("Booking active is set to false: {}", booking);
        return BOOKING_MAPPER_INSTANCE.mapEntityToResponse(booking);
    }

    @Override
    public List<BookingRsModel> deleteBooking(UUID flightId) {
        var bookings = bookingRepo.findAllByFlightId(flightId);

        var response = new ArrayList<BookingRsModel>();

        bookings.forEach(booking -> {
            booking.setActive(false);
            log.info("Booking active is set to false: {}", booking);
            response.add(BOOKING_MAPPER_INSTANCE.mapEntityToResponse(booking));
        });

        return response;
    }

    private Booking bookingByIdAndUser(UUID id, User user) {
        var booking = bookingRepo.findByBookingIdAndUserAndActiveTrue(id, user).orElseThrow(
                () -> new FlightException("Booking not found by given ID and User"));

        log.info("Booking by ID '{}' and user '{}': {}", id, user, booking);
        return booking;
    }
}
