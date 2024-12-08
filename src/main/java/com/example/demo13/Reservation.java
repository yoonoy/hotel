package com.example.demo13;

import java.util.Date;

public class Reservation extends Entity {
    private int roomId;
    private String customerName;
    private Date checkIn;
    private Date checkOut;

    public Reservation(int id, int roomId, String customerName, Date checkIn, Date checkOut) {
        super(id);
        this.roomId = roomId;
        this.customerName = customerName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String getDetails() {
        return "Reservation ID: " + getId() + ", Room ID: " + roomId +
                ", Customer Name: " + customerName + ", Check-In: " + checkIn + ", Check-Out: " + checkOut;
    }
}