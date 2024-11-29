package com.example.demo13;

public class Room {
    private int roomId;
    private int floor;
    private String roomType;
    private double price;
    private boolean isAvailable;


    public Room(int roomId, int floor, String roomType, double price, boolean isAvailable) {
        this.roomId = roomId;
        this.floor = floor;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = isAvailable;
    }


    public int getRoomId() {
        return roomId;
    }



    @Override
    public String toString() {
        return "Room ID: " + roomId + ", Type: " + roomType + ", Price: " + price + ", Available: " + isAvailable;
    }
}
