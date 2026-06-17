package com.roomsync.service;

import com.roomsync.exception.ResourceNotFoundException;
import com.roomsync.repository.RoomRepository;
import com.roomsync.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room not found"));
    }

    public Room updateRoom(String id, Room updatedRoom) {

        Room room = getRoomById(id);

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setRoomType(updatedRoom.getRoomType());
        room.setPrice(updatedRoom.getPrice());
        room.setStatus(updatedRoom.getStatus());

        return roomRepository.save(room);
    }

    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }
}