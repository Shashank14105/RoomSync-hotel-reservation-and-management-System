package com.roomsync.controller;

import com.roomsync.model.Room;
import com.roomsync.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @GetMapping
    public List<Room> getRooms() {

        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable String id) {
        return roomService.getRoomById(id);
    }

    @PutMapping("/{id}")
    public Room updateRoom(
            @PathVariable String id,
            @RequestBody Room room) {

        return roomService.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable String id) {

        roomService.deleteRoom(id);

        return "Room deleted successfully";
    }
}