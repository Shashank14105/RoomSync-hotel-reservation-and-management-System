package com.roomsync.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String customerId;

    private String roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String bookingStatus;
}