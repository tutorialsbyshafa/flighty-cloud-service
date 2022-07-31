package com.tutorials.msbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "public", name = "flight")
public class Flight {

    @ToString.Exclude
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

    @Column(name = "active")
    Boolean active;

    @ToString.Exclude
    @OneToMany(mappedBy = "flight")
    List<Booking> bookings;
}
