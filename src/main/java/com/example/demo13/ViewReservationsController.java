package com.example.demo13;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewReservationsController {

    @FXML
    private TableView<Reservation> reservationsTable;

    @FXML
    private TableColumn<Reservation, Integer> roomIdColumn;

    @FXML
    private TableColumn<Reservation, String> guestNameColumn;

    @FXML
    private TableColumn<Reservation, String> reservationDateColumn;

    @FXML
    private TableColumn<Reservation, Integer> stayDurationColumn;

    @FXML
    public void initialize() {
        // Set up the columns to display data
        roomIdColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        guestNameColumn.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        reservationDateColumn.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        stayDurationColumn.setCellValueFactory(new PropertyValueFactory<>("stayDuration"));

        // Fetch and display reservation data
        loadReservations();
    }

    private void loadReservations() {
        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int roomId = resultSet.getInt("room_id");
                String guestName = resultSet.getString("guest_name");
                Date reservationDate = resultSet.getDate("reservation_date");
                int stayDuration = resultSet.getInt("stay_duration");

                // Format the date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(reservationDate);

                // Add data to the table
                reservationsTable.getItems().add(new Reservation(roomId, guestName, formattedDate, stayDuration));
            }

        } catch (SQLException e) {
            showAlert("Database Error", "Unable to fetch reservation data: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBack() {
        // Code to go back to the main view (hello-view.fxml)
    }
}
