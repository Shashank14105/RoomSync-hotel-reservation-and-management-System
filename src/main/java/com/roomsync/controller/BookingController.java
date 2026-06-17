package com.roomsync.controller;

import com.roomsync.model.Booking;
import com.roomsync.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(
            BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(
            @RequestBody Booking booking) {

        return bookingService.createBooking(
                booking);
    }

    @GetMapping
    public List<Booking> getBookings() {

        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(
            @PathVariable String id) {

        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(
            @PathVariable String id,
            @RequestBody Booking booking) {

        return bookingService.updateBooking(id, booking);
    }

    @PutMapping("/cancel/{id}")
    public Booking cancelBooking(
            @PathVariable String id) {

        return bookingService.cancelBooking(id);
    }

    @PutMapping("/checkin/{id}")
    public Booking checkIn(
            @PathVariable String id) {

        return bookingService.checkIn(id);
    }

    @PutMapping("/checkout/{id}")
    public Booking checkOut(
            @PathVariable String id) {

        return bookingService.checkOut(id);
    }
}