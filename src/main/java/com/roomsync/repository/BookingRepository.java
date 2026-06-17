package com.roomsync.repository;

import com.roomsync.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository
        extends MongoRepository<Booking,String> {

    List<Booking> findByRoomId(String roomId);
}