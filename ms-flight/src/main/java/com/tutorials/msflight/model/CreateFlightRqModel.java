package com.tutorials.msflight.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateFlightRqModel {
    @NotBlank
    String arrivalTime;

    @NotBlank
    String departureTime;

    @NotNull
    BigDecimal price;

    @NotNull
    UUID arrivalLocation;

    @NotNull
    UUID departureLocation;

    @AssertTrue(message = "Arrival and departure locations must be different")
    private boolean areLocationsValid() {
        return ObjectUtils.allNotNull(arrivalLocation, departureLocation) &&
                !arrivalLocation.equals(departureLocation);
    }

    @AssertTrue(message = "Departure time must be before arrival time")
    private boolean isTimeValid() {
        return ObjectUtils.allNotNull(arrivalTime, departureTime) &&
                LocalDateTime.parse(departureTime).isBefore(LocalDateTime.parse(arrivalTime));
    }
}
