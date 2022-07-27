package com.tutorials.msflight.service.impl;

import static com.tutorials.msflight.mapper.FlightMapper.FLIGHT_MAPPER;

import com.tutorials.msflight.entity.Location;
import com.tutorials.msflight.exception.AppException;
import com.tutorials.msflight.model.CreateFlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import com.tutorials.msflight.repo.FlightRepository;
import com.tutorials.msflight.repo.LocationRepository;
import com.tutorials.msflight.service.FlightService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Log4j2
@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final LocationRepository locationRepository;

    @Override
    public FlightRsModel createFlight(CreateFlightRqModel request) {
        var arrivalLocation = findLocation(request.getArrivalLocation());
        var departureLocation = findLocation(request.getDepartureLocation());

        var flight = FLIGHT_MAPPER.mapRequestToEntity(request);
        flight.setArrivalLocation(arrivalLocation);
        flight.setDepartureLocation(departureLocation);

        flightRepository.save(flight);

        var response = FLIGHT_MAPPER.mapEntityToResponse(flight);
        response.setArrivalLocation(FLIGHT_MAPPER.mapLocationModel(arrivalLocation));
        response.setDepartureLocation(FLIGHT_MAPPER.mapLocationModel(departureLocation));
        return response;
    }

    private Location findLocation(UUID locationId) {
        return locationRepository.findLocationByLocationId(locationId)
                .orElseThrow(() -> new AppException("location not found by given id"));
    }
}
