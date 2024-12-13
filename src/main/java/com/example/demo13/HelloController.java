package com.example.demo13;

import com.example.demo13.DataConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
            // Validate if the roomId is an integer
            if (!isInteger(roomId)) {
                showAlert("Invalid Input", "Room ID must be an integer. Please try again.", Alert.AlertType.ERROR);
                return;
            }

            try (Connection connection = DataConnection.getConnection()) {
                // Check if the room is available
                PreparedStatement checkRoomStmt = connection.prepareStatement(
                        "SELECT * FROM rooms WHERE room_id = ? AND is_available = true");
                checkRoomStmt.setInt(1, Integer.parseInt(roomId));
                ResultSet roomCheckResult = checkRoomStmt.executeQuery();

                if (!roomCheckResult.next()) {
                    showAlert("Room Unavailable", "Room ID " + roomId + " is not available. Please select a different room.", Alert.AlertType.ERROR);
                    return;
                }

                // Proceed with reservation
                TextInputDialog nameDialog = new TextInputDialog();
                nameDialog.setTitle("Enter Your Name");
                nameDialog.setHeaderText("Enter Your Name for Reservation");
                nameDialog.setContentText("Name:");

                Optional<String> nameInput = nameDialog.showAndWait();
                nameInput.ifPresent(name -> {
                    // Validate if the name contains any numbers
                    if (!isValidName(name)) {
                        showAlert("Invalid Input", "Name cannot contain numbers. Please enter a valid name.", Alert.AlertType.ERROR);
                        return;
                    }

                    TextInputDialog daysDialog = new TextInputDialog();
                    daysDialog.setTitle("Stay Duration");
                    daysDialog.setHeaderText("Enter Number of Days of Stay");
                    daysDialog.setContentText("Days:");

                    Optional<String> daysInput = daysDialog.showAndWait();
                    daysInput.ifPresent(days -> {
                        // Validate if the number of days is an integer
                        if (!isInteger(days)) {
                            showAlert("Invalid Input", "Number of days must be an integer. Please try again.", Alert.AlertType.ERROR);
                            return;
                        }

                        try (PreparedStatement insertStmt = connection.prepareStatement(
                                "INSERT INTO reservations (room_id, guest_name, stay_duration) VALUES (?, ?, ?)")) {

                            // Insert reservation
                            insertStmt.setInt(1, Integer.parseInt(roomId));
                            insertStmt.setString(2, name);
                            insertStmt.setInt(3, Integer.parseInt(days));
                            int rowsInserted = insertStmt.executeUpdate();

                            if (rowsInserted > 0) {
                                // Mark room as unavailable
                                PreparedStatement updateRoomStmt = connection.prepareStatement(
                                        "UPDATE rooms SET is_available = false WHERE room_id = ?");
                                updateRoomStmt.setInt(1, Integer.parseInt(roomId));
                                updateRoomStmt.executeUpdate();

                                showAlert("Reservation Successful", "Room ID " + roomId + " reserved for " + name + " for " + days + " days.");
                            } else {
                                showAlert("Reservation Failed", "Unable to reserve room.");
                            }

                        } catch (SQLException e) {
                            showAlert("Database Error", "Unable to make reservation: " + e.getMessage(), Alert.AlertType.ERROR);
                        }
                    });
                });
            } catch (SQLException e) {
                showAlert("Database Error", "Unable to check room availability: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        });
    }

    // Helper method to check if a string is an integer
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Helper method to check if the name contains only letters and spaces
    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z\\s]+");
    }




    // View Reservations Handler
    @FXML
    private void handleViewReservations() {
        StringBuilder reservationsInfo = new StringBuilder("All Reservations:\n");

        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id"); // Fetch reservation ID
                int roomId = resultSet.getInt("room_id");
                String guestName = resultSet.getString("guest_name");
                int stayDuration = resultSet.getInt("stay_duration");
                Date reservationDate = resultSet.getDate("reservation_date");

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(reservationDate);

                reservationsInfo.append("Reservation ID: ").append(reservationId) // Include reservation ID
                        .append(", Room ID: ").append(roomId)
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
                // Validate the room type input
                if (!isValidRoomType(roomType)) {
                    showAlert("Invalid Room Type", "Invalid room type entered. Please enter 'Single', 'Double', or 'Suite'.", Alert.AlertType.ERROR);
                    return;  // Exit if the room type is invalid
                }

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

    // Helper method to validate the room type
    private boolean isValidRoomType(String roomType) {
        return roomType != null && (roomType.equalsIgnoreCase("Single") ||
                roomType.equalsIgnoreCase("Double") ||
                roomType.equalsIgnoreCase("Suite"));
    }


    @FXML
    private void handleCancelReservation() {
        // Ask user for reservation ID to cancel
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Cancel Reservation");
        dialog.setHeaderText("Enter Reservation ID to Cancel");
        dialog.setContentText("Reservation ID:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(reservationId -> {
            try (Connection connection = DataConnection.getConnection()) {
                // Check if the reservation exists before attempting deletion
                PreparedStatement checkStmt = connection.prepareStatement(
                        "SELECT * FROM reservations WHERE reservation_id = ?");
                checkStmt.setInt(1, Integer.parseInt(reservationId));
                ResultSet checkResult = checkStmt.executeQuery();

                if (checkResult.next()) {
                    // Reservation found, proceed with deletion
                    PreparedStatement deleteStmt = connection.prepareStatement(
                            "DELETE FROM reservations WHERE reservation_id = ?");
                    deleteStmt.setInt(1, Integer.parseInt(reservationId));

                    int rowsDeleted = deleteStmt.executeUpdate();
                    if (rowsDeleted > 0) {
                        showAlert("Reservation Canceled", "Reservation ID " + reservationId + " has been canceled.");
                    } else {
                        showAlert("Cancellation Failed", "Unable to cancel reservation.");
                    }
                } else {
                    showAlert("Reservation Not Found", "No reservation found with the given ID.");
                }
            } catch (SQLException e) {
                showAlert("Database Error", "Unable to cancel reservation: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        });
    }
    @FXML
    private void handleCancelAllReservations() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Update Reservations");
        confirmationAlert.setHeaderText("Are you sure you want to cancel all reservations?");
        confirmationAlert.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try (Connection connection = DataConnection.getConnection();
                 PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM reservations");
                 PreparedStatement updateRoomStmt = connection.prepareStatement("UPDATE rooms SET is_available = true")) {

                // Delete all reservations
                int rowsDeleted = deleteStmt.executeUpdate();

                // Mark all rooms as available
                updateRoomStmt.executeUpdate();

                if (rowsDeleted > 0) {
                    showAlert("All Reservations Canceled", "All reservations have been successfully canceled.");
                } else {
                    showAlert("No Reservations Found", "There were no reservations to cancel.");
                }

            } catch (SQLException e) {
                showAlert("Database Error", "Unable to cancel all reservations: " + e.getMessage(), Alert.AlertType.ERROR);
            }
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