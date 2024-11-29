package com.example.demo13;

import javafx.scene.control.Alert;

public class RoomController {
    public void displayRoomDetails(Room room) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Room Details");
        alert.setHeaderText("Room Information");
        alert.setContentText(room.toString());
        alert.showAndWait();
    }
}