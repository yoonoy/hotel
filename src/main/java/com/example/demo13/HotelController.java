package com.example.demo13;

import javafx.scene.control.Alert;

public class HotelController {
    public void displayHotelDetails(Hotel hotel) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hotel Details");
        alert.setHeaderText("Hotel Information");
        alert.setContentText("Hotel Name: " + hotel.getName() + "\nLocation: " + hotel.getLocation());
        alert.showAndWait();
    }
}