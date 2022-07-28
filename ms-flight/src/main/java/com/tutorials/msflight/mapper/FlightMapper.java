package com.tutorials.msflight.mapper;

import com.tutorials.msflight.entity.Flight;
import com.tutorials.msflight.entity.Location;
import com.tutorials.msflight.model.CreateFlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import com.tutorials.msflight.model.LocationModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class FlightMapper {

    public static final FlightMapper FLIGHT_MAPPER = Mappers.getMapper(FlightMapper.class);

    @Mapping(target = "arrivalTime", ignore = true)
    @Mapping(target = "departureTime", ignore = true)
    @Mapping(target = "arrivalLocation", ignore = true)
    @Mapping(target = "departureLocation", ignore = true)
    public abstract Flight mapRequestToEntity(CreateFlightRqModel request);

    @AfterMapping
    void mapAdditionalValues(CreateFlightRqModel request, @MappingTarget Flight.FlightBuilder flight) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        flight.arrivalTime(LocalDateTime.parse(request.getArrivalTime(), formatter));
        flight.departureTime(LocalDateTime.parse(request.getDepartureTime(), formatter));
    }

    @Mapping(target = "id", source = "flightId")
    public abstract FlightRsModel mapEntityToResponse(Flight flight);

    @AfterMapping
    void mapAdditionalValues(Flight flight, @MappingTarget FlightRsModel.FlightRsModelBuilder response) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        response.arrivalTime(flight.getArrivalTime().format(formatter));
        response.departureTime(flight.getDepartureTime().format(formatter));
    }

    @Mapping(target = "id", source = "locationId")
    public abstract LocationModel mapLocationModel(Location location);
}
