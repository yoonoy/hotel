package com.example.demo13;

import javafx.scene.control.Alert;

public class ReservationController {
    public void displayReservationDetails(Reservation reservation) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reservation Details");
        alert.setHeaderText("Reservation Information");
        alert.setContentText(reservation.toString());
        alert.showAndWait();
    }
}