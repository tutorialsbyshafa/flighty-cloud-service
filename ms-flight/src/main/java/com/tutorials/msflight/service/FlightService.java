package com.tutorials.msflight.service;

import com.tutorials.msflight.model.CreateFlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;

public interface FlightService {
    FlightRsModel createFlight(CreateFlightRqModel request);
}
