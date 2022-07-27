package com.tutorials.msflight.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FlightRsModel {
    UUID id;
    String arrivalTime;
    String departureTime;
    BigDecimal price;
    LocationModel arrivalLocation;
    LocationModel departureLocation;
}
