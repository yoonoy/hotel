package com.example.demo13;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class HelloController {
    private final DataConnection dataConnection;

    public HelloController(DataConnection dataConnection) {
        this.dataConnection = dataConnection;
    }

    @FXML
    private ListView<String> reservationListView;

    // View Rooms Handler
    @FXML
    private void handleViewRooms() {
        StringBuilder roomsInfo = new StringBuilder("Available Rooms:\n");
        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms WHERE is_available = true")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                roomsInfo.append("Room ID: ").append(resultSet.getInt("room_id"))
                        .append(", Type: ").append(resultSet.getString("room_type"))
                        .append(", Price: $").append(resultSet.getDouble("price_per_night"))
                        .append("\n");
            }

            showAlert("View Rooms", roomsInfo.toString());

        } catch (SQLException e) {
            showAlert("Database Error", "Unable to fetch room data: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Make Reservation Handler
    @FXML
    private void handleMakeReservation() {
        TextInputDialog roomDialog = new TextInputDialog();
        roomDialog.setTitle("Room Selection");
        roomDialog.setHeaderText("Enter Room ID to Reserve");
        roomDialog.setContentText("Room ID:");

        Optional<String> roomIdInput = roomDialog.showAndWait();
        roomIdInput.ifPresent(roomId -> {
            TextInputDialog nameDialog = new TextInputDialog();
            nameDialog.setTitle("Enter Your Name");
            nameDialog.setHeaderText("Enter Your Name for Reservation");
            nameDialog.setContentText("Name:");

            Optional<String> nameInput = nameDialog.showAndWait();
            nameInput.ifPresent(name -> {
                try (Connection connection = DataConnection.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "INSERT INTO reservations (room_id, guest_name) VALUES (?, ?)")) {

                    statement.setInt(1, Integer.parseInt(roomId));
                    statement.setString(2, name);
                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                        showAlert("Reservation Successful", "Room ID " + roomId + " reserved for " + name);
                    } else {
                        showAlert("Reservation Failed", "Unable to reserve room.");
                    }

                } catch (SQLException e) {
                    showAlert("Database Error", "Unable to make reservation: " + e.getMessage(), Alert.AlertType.ERROR);
                }
            });
        });
    }

    // View Reservations Handler
    @FXML
    private void handleViewReservations() {
        reservationListView.getItems().clear();

        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String reservationInfo = "Reservation ID: " + resultSet.getInt("reservation_id")
                        + ", Room ID: " + resultSet.getInt("room_id")
                        + ", Guest Name: " + resultSet.getString("guest_name");
                reservationListView.getItems().add(reservationInfo);
            }

            showAlert("View Reservations", "Reservations loaded successfully.");

        } catch (SQLException e) {
            showAlert("Database Error", "Unable to fetch reservations: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // View Hotel Information Handler
    @FXML
    private void handleViewHotels() {
        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM hotels WHERE hotel_id = 1")) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String hotelInfo = "Hotel Name: " + resultSet.getString("hotel_name")
                        + ", Location: " + resultSet.getString("location");
                showAlert("Hotel Information", hotelInfo, "Detailed Hotel Information");
            } else {
                showAlert("No Hotel Found", "No hotel data available.");
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Unable to fetch hotel information: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Exit Handler
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    // Overloaded showAlert Method
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}