package com.lcaohoanq.fucar.controllers;

import com.lcaohoanq.fucar.constants.ResourcePaths;
import com.lcaohoanq.fucar.models.Car;
import com.lcaohoanq.fucar.models.CarRental;
import com.lcaohoanq.fucar.services.ICarRentalService;
import com.lcaohoanq.fucar.services.CarRentalService;
import java.math.BigDecimal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class TransactionReportController {

    private final ICarRentalService carRentalService;
    private ObservableList<CarRental> tableModel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button generateReportButton;

    @FXML
    private TableView<CarRental> reportTable;

    @FXML
    private TableColumn<CarRental, Long> rentalIdColumn;

    @FXML
    private TableColumn<CarRental, String> customerNameColumn;

    @FXML
    private TableColumn<CarRental, String> carNameColumn;

    @FXML
    private TableColumn<CarRental, LocalDate> pickupDateColumn;

    @FXML
    private TableColumn<CarRental, LocalDate> returnDateColumn;

    @FXML
    private TableColumn<CarRental, BigDecimal> rentPriceColumn;

    public TransactionReportController() {
        this.carRentalService = new CarRentalService(ResourcePaths.HIBERNATE_CONFIG);
    }

    @FXML
    public void initialize() {
        // Set up table columns
        initializeTableColumns();
        refreshDataTable();

//        reportTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newSelection != null) {
//                showReportData(newValue);
//            }
//        });

        // Set button action
        generateReportButton.setOnAction(event -> generateReport());
    }

//    private void showReportData(CarRental carRental) {
//        // Display the selected car rental data in the input fields
//        startDatePicker.setValue(carRental.getPickupDate());
//        endDatePicker.setValue(carRental.getReturnDate());
//    }

    private void initializeTableColumns() {
        rentalIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customer.name"));
        carNameColumn.setCellValueFactory(new PropertyValueFactory<>("car.name"));
        pickupDateColumn.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        rentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
    }

    private void refreshDataTable() {
        tableModel.setAll(carRentalService.findAll());
        reportTable.setItems(tableModel);
        clearInputFields();
    }

    private void clearInputFields() {
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
    }

    private void generateReport() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate != null && endDate != null) {
            List<CarRental> rentals = carRentalService.findRentalsByDateRange(startDate, endDate);
            ObservableList<CarRental> observableRentals = FXCollections.observableArrayList(rentals);

            // Sort by rent price in descending order
            observableRentals.sort(Comparator.comparing(CarRental::getRentPrice).reversed());

            reportTable.setItems(observableRentals);
        } else {
            // Handle error: prompt user to select valid dates
            showAlert("Invalid Date Selection", "Please select both start and end dates.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
