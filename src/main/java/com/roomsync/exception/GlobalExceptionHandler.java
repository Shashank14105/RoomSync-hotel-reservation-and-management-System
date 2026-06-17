package com.roomsync.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleResourceNotFound(
            ResourceNotFoundException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        LocalDateTime.now());

        return new ResponseEntity<>(
                error,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGenericException(
            Exception ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        LocalDateTime.now());

        return new ResponseEntity<>(
                error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<ErrorResponse>
    handleRoomNotAvailable(
            RoomNotAvailableException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.CONFLICT.value(),
                        LocalDateTime.now());

        return new ResponseEntity<>(
                error,
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingOperationException.class)
    public ResponseEntity<ErrorResponse>
    handleBookingOperation(
            BookingOperationException ex) {

        ErrorResponse error =
                new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        LocalDateTime.now());

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST);
    }
}