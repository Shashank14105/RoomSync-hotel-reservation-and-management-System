package com.roomsync.exception;

public class BookingOperationException
        extends RuntimeException {

    public BookingOperationException(
            String message) {
        super(message);
    }
}