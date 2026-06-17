package com.roomsync.repository;

import com.roomsync.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String>{
}
