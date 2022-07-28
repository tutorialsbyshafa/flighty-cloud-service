package com.tutorials.msflight.service.impl;

import static com.tutorials.msflight.mapper.FlightMapper.FLIGHT_MAPPER;

import com.tutorials.msflight.entity.Flight;
import com.tutorials.msflight.entity.Location;
import com.tutorials.msflight.exception.AppException;
import com.tutorials.msflight.model.CreateFlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import com.tutorials.msflight.model.UpdateFlightRqModel;
import com.tutorials.msflight.repo.FlightRepository;
import com.tutorials.msflight.repo.LocationRepository;
import com.tutorials.msflight.service.FlightService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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
        var departureLocation = findLocation(request.getDepartureLocation());
        var arrivalLocation = findLocation(request.getArrivalLocation());
        var code = generateFlightCode(departureLocation, arrivalLocation);

        var flight = FLIGHT_MAPPER.mapRequestToEntity(request);
        flight.setArrivalLocation(arrivalLocation);
        flight.setDepartureLocation(departureLocation);
        flight.setCode(code);

        flightRepository.save(flight);

        return getFlightResponse(departureLocation, arrivalLocation, flight);
    }

    @Override
    public FlightRsModel updateFlight(UpdateFlightRqModel request) {
        var departureLocation = findLocation(request.getDepartureLocation());
        var arrivalLocation = findLocation(request.getArrivalLocation());
        var code = generateFlightCode(departureLocation, arrivalLocation);

        var flight = findFlight(request.getId());
        updateFlight(request, departureLocation, arrivalLocation, code, flight);

        flightRepository.save(flight);

        return getFlightResponse(departureLocation, arrivalLocation, flight);
    }

    @Override
    public List<FlightRsModel> getFlights() {
        var flights = flightRepository.findAllByActiveTrue();

        return flights.stream()
                .map(FLIGHT_MAPPER::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    private void updateFlight(UpdateFlightRqModel request, Location departureLocation, Location arrivalLocation,
                           String code, Flight flight) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        flight.setArrivalLocation(arrivalLocation);
        flight.setDepartureLocation(departureLocation);
        flight.setCode(code);
        flight.setPrice(request.getPrice());
        flight.setArrivalTime(LocalDateTime.parse(request.getArrivalTime(), formatter));
        flight.setDepartureTime(LocalDateTime.parse(request.getDepartureTime(), formatter));
    }

    private Location findLocation(UUID id) {
        return locationRepository.findLocationByLocationId(id)
                .orElseThrow(() -> new AppException("location not found by given id"));
    }

    private Flight findFlight(UUID id) {
        return flightRepository.findFlightByFlightIdAndActiveTrue(id)
                .orElseThrow(() -> new AppException("flight not found by given id"));
    }

    private String generateFlightCode(Location departureLocation, Location arrivalLocation) {
        return String.format("%s%s%s",
                departureLocation.getCity().substring(0, 1).toUpperCase(),
                arrivalLocation.getCity().substring(0, 1).toUpperCase(),
                LocalDateTime.now());
    }

    private FlightRsModel getFlightResponse(Location departureLocation, Location arrivalLocation, Flight flight) {
        var response = FLIGHT_MAPPER.mapEntityToResponse(flight);
        response.setArrivalLocation(FLIGHT_MAPPER.mapLocationModel(arrivalLocation));
        response.setDepartureLocation(FLIGHT_MAPPER.mapLocationModel(departureLocation));
        return response;
    }

}
