package com.lcaohoanq.fucar.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.lcaohoanq.fucar.constants.ResourcePaths;
import com.lcaohoanq.fucar.controllers.LoginController;
import com.lcaohoanq.fucar.services.AccountService;

public class LoginView extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Create the AccountService
            AccountService accountService = new AccountService(ResourcePaths.HIBERNATE_CONFIG);

            // Create the controller with the service
            LoginController controller = new LoginController(accountService);

            // Create the FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourcePaths.LOGIN_VIEW));

            // Set the controller before loading the FXML
            loader.setController(controller);

            // Load the view
            Parent root = loader.load();

            // Create and configure the scene
            Scene scene = new Scene(root, 830, 650);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initializing Login: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}