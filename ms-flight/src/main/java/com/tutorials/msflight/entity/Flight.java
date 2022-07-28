package com.tutorials.msflight.entity;

import com.tutorials.msflight.enums.FlightStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "public", name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Generated(value = GenerationTime.ALWAYS)
    @Column(name = "flight_id")
    UUID flightId;

    @Column(name = "code")
    String code;

    @Column(name = "arrival_time")
    LocalDateTime arrivalTime;

    @Column(name = "departure_time")
    LocalDateTime departureTime;

    @Column(name = "price")
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "arrival_location", referencedColumnName = "id")
    Location arrivalLocation;

    @ManyToOne
    @JoinColumn(name = "departure_location", referencedColumnName = "id")
    Location departureLocation;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    FlightStatus status;

    @Column(name = "active")
    Boolean active;

    @PrePersist
    private void setPreValues() {
        if (status == null)
            status = FlightStatus.SCHEDULED;

        if (active == null)
            active = true;
    }


}
