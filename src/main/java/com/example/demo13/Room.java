package com.example.demo13;

public class Room extends Entity {
    private int floor;
    private String roomType;
    private double price;
    private boolean isAvailable;

    public Room(int id, int floor, String roomType, double price, boolean isAvailable) {
        super(id);
        this.floor = floor;
        this.roomType = roomType;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String getDetails() {
        return "Room ID: " + getId() + ", Floor: " + floor + ", Type: " + roomType +
                ", Price: $" + price + ", Available: " + isAvailable;
    }
}