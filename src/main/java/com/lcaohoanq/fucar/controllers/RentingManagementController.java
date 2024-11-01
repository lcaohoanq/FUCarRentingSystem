package com.lcaohoanq.fucar.controllers;

import com.lcaohoanq.fucar.constants.ResourcePaths;
import com.lcaohoanq.fucar.models.CarRental;
import com.lcaohoanq.fucar.models.Customer;
import com.lcaohoanq.fucar.models.Car;
import com.lcaohoanq.fucar.enums.ERentalStatus;
import com.lcaohoanq.fucar.services.CarService;
import com.lcaohoanq.fucar.services.CustomerService;
import com.lcaohoanq.fucar.services.ICarService;
import com.lcaohoanq.fucar.services.ICustomerService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RentingManagementController {

    private final ICustomerService customerService;
    private final ICarService carService;

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private ComboBox<Car> carComboBox;

    @FXML
    private DatePicker pickupDatePicker;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private TextField rentPriceField;

    @FXML
    private ComboBox<ERentalStatus> statusComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    // TableView and columns for displaying cars
    @FXML
    private TableView<Car> tblCars;

    @FXML
    private TableColumn<Car, Integer> carId;

    @FXML
    private TableColumn<Car, String> carName;

    @FXML
    private TableColumn<Car, Integer> carModelYear;

    @FXML
    private TableColumn<Car, String> color;

    @FXML
    private TableColumn<Car, Integer> capacity;

    @FXML
    private TableColumn<Car, String> description;

    @FXML
    private TableColumn<Car, LocalDate> importDate;

    @FXML
    private TableColumn<Car, BigDecimal> rentPrice;

    @FXML
    private TableColumn<Car, String> producer;

    private ObservableList<Car> carList;

    public RentingManagementController(){
        this.customerService = new CustomerService(ResourcePaths.HIBERNATE_CONFIG);
        this.carService = new CarService(ResourcePaths.HIBERNATE_CONFIG);
    }

    public void initialize() {
        // Load data into ComboBoxes
        loadCustomers();
        loadCars();
        loadRentalStatuses();

        // Set up table columns
        setUpTableColumns();

        // Load data into table
        loadCarTable();

        // Save button action
        saveButton.setOnAction(event -> saveRental());

        // Cancel button action
        cancelButton.setOnAction(event -> Platform.exit());
    }

    private void loadCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.findAll());
        customerComboBox.setItems(customers);
    }

    private void loadCars() {
        ObservableList<Car> cars = FXCollections.observableArrayList(carService.findAll());
        carComboBox.setItems(cars);
    }

    private void loadRentalStatuses() {
        statusComboBox.setItems(FXCollections.observableArrayList(ERentalStatus.values()));
    }

    private void setUpTableColumns() {
        carId.setCellValueFactory(new PropertyValueFactory<>("id"));
        carName.setCellValueFactory(new PropertyValueFactory<>("name"));
        carModelYear.setCellValueFactory(new PropertyValueFactory<>("modelYear"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        rentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        producer.setCellValueFactory(new PropertyValueFactory<>("producer"));
    }

    private void loadCarTable() {
        carList = FXCollections.observableArrayList(carService.findAll());
        tblCars.setItems(carList);
    }

    private void saveRental() {
        // Extract values from form fields
        Customer customer = customerComboBox.getValue();
        Car car = carComboBox.getValue();
        LocalDate pickupDate = pickupDatePicker.getValue();
        LocalDate returnDate = returnDatePicker.getValue();
        BigDecimal rentPrice = new BigDecimal(rentPriceField.getText());
        ERentalStatus status = statusComboBox.getValue();

        // Build CarRental object
        CarRental carRental = CarRental.builder()
            .customer(customer)
            .car(car)
            .pickupDate(java.sql.Date.valueOf(pickupDate))
            .returnDate(java.sql.Date.valueOf(returnDate))
            .rentPrice(rentPrice)
            .status(status)
            .build();

        // Save to database or perform necessary actions
        System.out.println("Saving rental: " + carRental);
    }
}
