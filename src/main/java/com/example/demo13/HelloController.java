package com.example.demo13;

import com.example.demo13.DataConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class HelloController {

    @FXML
    private ListView<String> reservationListView;  // ListView to display reservations

    // View Rooms Handler
    @FXML
    protected void handleViewRooms() {
        StringBuilder roomsInfo = new StringBuilder("Available Rooms:\n");
        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms WHERE is_available = true ORDER BY room_type")) {

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
                TextInputDialog daysDialog = new TextInputDialog();
                daysDialog.setTitle("Stay Duration");
                daysDialog.setHeaderText("Enter Number of Days of Stay");
                daysDialog.setContentText("Days:");

                Optional<String> daysInput = daysDialog.showAndWait();
                daysInput.ifPresent(days -> {
                    try (Connection connection = DataConnection.getConnection();
                         PreparedStatement statement = connection.prepareStatement(
                                 "INSERT INTO reservations (room_id, guest_name, stay_duration) VALUES (?, ?, ?)")) {

                        statement.setInt(1, Integer.parseInt(roomId));
                        statement.setString(2, name);
                        statement.setInt(3, Integer.parseInt(days));
                        int rowsInserted = statement.executeUpdate();

                        if (rowsInserted > 0) {
                            showAlert("Reservation Successful", "Room ID " + roomId + " reserved for " + name + " for " + days + " days.");
                        } else {
                            showAlert("Reservation Failed", "Unable to reserve room.");
                        }

                    } catch (SQLException e) {
                        showAlert("Database Error", "Unable to make reservation: " + e.getMessage(), Alert.AlertType.ERROR);
                    }
                });
            });
        });
    }

    // View Reservations Handler
    @FXML
    private void handleViewReservations() {
        StringBuilder reservationsInfo = new StringBuilder("All Reservations:\n");

        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                String guestName = resultSet.getString("guest_name");
                int stayDuration = resultSet.getInt("stay_duration");
                Date reservationDate = resultSet.getDate("reservation_date");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(reservationDate);

                reservationsInfo.append("Room ID: ").append(roomId)
                        .append(", Guest Name: ").append(guestName)
                        .append(", Reservation Date: ").append(formattedDate)
                        .append(", Stay Duration: ").append(stayDuration).append(" days")
                        .append("\n");
            }

            showAlert("View Reservations", reservationsInfo.toString());

        } catch (SQLException e) {
            showAlert("Database Error", "Unable to fetch reservation data: " + e.getMessage(), Alert.AlertType.ERROR);
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

    // Add Rooms Handler
    @FXML
    private void handleAddRooms() {
        StringBuilder roomCountInfo = new StringBuilder("Available Rooms Count by Type:\n");
        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT room_type, COUNT(*) as available_count FROM rooms WHERE is_available = true GROUP BY room_type ORDER BY room_type")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roomType = resultSet.getString("room_type");
                int availableCount = resultSet.getInt("available_count");
                roomCountInfo.append("Room Type: ").append(roomType)
                        .append(", Available: ").append(availableCount)
                        .append("\n");
            }

            showAlert("Room Count Information", roomCountInfo.toString());

            // Ask for new room details
            TextInputDialog roomTypeDialog = new TextInputDialog();
            roomTypeDialog.setTitle("Room Type");
            roomTypeDialog.setHeaderText("Enter the type of room you want to add (Single, Double, Suite):");
            Optional<String> roomTypeInput = roomTypeDialog.showAndWait();
            roomTypeInput.ifPresent(roomType -> {
                TextInputDialog roomIdDialog = new TextInputDialog();
                roomIdDialog.setTitle("Room ID");
                roomIdDialog.setHeaderText("Enter the Room ID to add:");
                Optional<String> roomIdInput = roomIdDialog.showAndWait();
                roomIdInput.ifPresent(roomId -> {
                    TextInputDialog priceDialog = new TextInputDialog();
                    priceDialog.setTitle("Price Per Night");
                    priceDialog.setHeaderText("Enter the price per night for this room:");
                    Optional<String> priceInput = priceDialog.showAndWait();
                    priceInput.ifPresent(price -> {
                        try (Connection connection1 = DataConnection.getConnection();
                             PreparedStatement insertStatement = connection1.prepareStatement(
                                     "INSERT INTO rooms (room_type, room_id, price_per_night, is_available, floor) VALUES (?, ?, ?, true,1)")) {

                            insertStatement.setString(1, roomType);
                            insertStatement.setInt(2, Integer.parseInt(roomId));
                            insertStatement.setDouble(3, Double.parseDouble(price));

                            int rowsInserted = insertStatement.executeUpdate();
                            if (rowsInserted > 0) {
                                showAlert("Room Added", "Room " + roomId + " (" + roomType + ") added successfully.");

                                // After adding the room, update the ListView
                                handleViewRooms();
                            } else {
                                showAlert("Room Add Failed", "Unable to add room.");
                            }
                        } catch (SQLException e) {
                            showAlert("Database Error", "Unable to add room: " + e.getMessage(), Alert.AlertType.ERROR);
                        }
                    });
                });
            });

        } catch (SQLException e) {
            showAlert("Database Error", "Unable to fetch room count data: " + e.getMessage(), Alert.AlertType.ERROR);
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