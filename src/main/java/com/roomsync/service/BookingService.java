package com.roomsync.service;

import com.roomsync.exception.BookingOperationException;
import com.roomsync.exception.ResourceNotFoundException;
import com.roomsync.exception.RoomNotAvailableException;

import com.roomsync.model.Booking;
import com.roomsync.model.BookingStatus;
import com.roomsync.model.Room;
import com.roomsync.model.RoomStatus;

import com.roomsync.repository.BookingRepository;
import com.roomsync.repository.CustomerRepository;
import com.roomsync.repository.RoomRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    public BookingService(
            BookingRepository bookingRepository,
            CustomerRepository customerRepository,
            RoomRepository roomRepository) {

        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    public Booking createBooking(Booking booking) {

        if (!booking.getCheckOutDate()
                .isAfter(booking.getCheckInDate())) {

            throw new BookingOperationException(
                    "Check-out date must be after check-in date");
        }

        customerRepository.findById(
                        booking.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found"));

        Room room = roomRepository.findById(
                        booking.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room not found"));

        if (!RoomStatus.AVAILABLE.equals(room.getStatus())) {

            throw new RoomNotAvailableException(
                    "Room is not available");
        }

        booking.setBookingStatus(
                BookingStatus.CONFIRMED);

        room.setStatus(
                RoomStatus.BOOKED);

        roomRepository.save(room);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {

        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Booking not found"));
    }

    public Booking updateBooking(
            String id,
            Booking updatedBooking) {

        Booking booking = getBookingById(id);

        if (!updatedBooking.getCheckOutDate()
                .isAfter(updatedBooking.getCheckInDate())) {

            throw new BookingOperationException(
                    "Check-out date must be after check-in date");
        }

        booking.setCheckInDate(
                updatedBooking.getCheckInDate());

        booking.setCheckOutDate(
                updatedBooking.getCheckOutDate());

        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(String bookingId) {

        Booking booking =
                bookingRepository.findById(bookingId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Booking not found"));

        if (BookingStatus.CHECKED_OUT
                .equals(booking.getBookingStatus())) {

            throw new BookingOperationException(
                    "Completed bookings cannot be cancelled");
        }

        if (BookingStatus.CANCELLED
                .equals(booking.getBookingStatus())) {

            throw new BookingOperationException(
                    "Booking already cancelled");
        }

        Room room =
                roomRepository.findById(
                                booking.getRoomId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Room not found"));

        booking.setBookingStatus(
                BookingStatus.CANCELLED);

        room.setStatus(
                RoomStatus.AVAILABLE);

        roomRepository.save(room);

        return bookingRepository.save(booking);
    }

    public Booking checkIn(String bookingId) {

        Booking booking =
                bookingRepository.findById(bookingId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Booking not found"));

        if (!BookingStatus.CONFIRMED
                .equals(booking.getBookingStatus())) {

            throw new BookingOperationException(
                    "Only confirmed bookings can check in");
        }

        Room room =
                roomRepository.findById(
                                booking.getRoomId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Room not found"));

        room.setStatus(
                RoomStatus.OCCUPIED);

        roomRepository.save(room);

        booking.setBookingStatus(
                BookingStatus.CHECKED_IN);

        return bookingRepository.save(booking);
    }

    public Booking checkOut(String bookingId) {

        Booking booking =
                bookingRepository.findById(bookingId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Booking not found"));

        if (!BookingStatus.CHECKED_IN
                .equals(booking.getBookingStatus())) {

            throw new BookingOperationException(
                    "Guest must check in first");
        }

        Room room =
                roomRepository.findById(
                                booking.getRoomId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Room not found"));

        room.setStatus(
                RoomStatus.AVAILABLE);

        roomRepository.save(room);

        booking.setBookingStatus(
                BookingStatus.CHECKED_OUT);

        return bookingRepository.save(booking);
    }
}