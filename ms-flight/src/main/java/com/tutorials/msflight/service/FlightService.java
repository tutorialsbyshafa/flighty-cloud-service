package com.tutorials.msflight.service;

import com.tutorials.msflight.model.CreateFlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import com.tutorials.msflight.model.UpdateFlightRqModel;
import java.util.List;

public interface FlightService {
    FlightRsModel createFlight(CreateFlightRqModel request);

    FlightRsModel updateFlight(UpdateFlightRqModel request);

    List<FlightRsModel> getFlights();
}
