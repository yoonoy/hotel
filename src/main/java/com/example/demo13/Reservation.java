package com.example.demo13;

public class Reservation {
    private int roomId;
    private String guestName;
    private String reservationDate;
    private int stayDuration;

    public Reservation(int roomId, String guestName, String reservationDate, int stayDuration) {
        this.roomId = roomId;
        this.guestName = guestName;
        this.reservationDate = reservationDate;
        this.stayDuration = stayDuration;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getStayDuration() {
        return stayDuration;
    }

    public void setStayDuration(int stayDuration) {
        this.stayDuration = stayDuration;
    }
}
