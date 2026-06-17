package com.roomsync.model;

import com.roomsync.exception.ResourceNotFoundException;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "rooms")
public class Room {

    @Id
    private String id;

    private String roomNumber;
    private String roomType;
    private double price;
    private String status;
}