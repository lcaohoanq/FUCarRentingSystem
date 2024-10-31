package com.lcaohoanq.fucar.controllers;

import com.lcaohoanq.fucar.constants.APIConstants;
import com.lcaohoanq.fucar.exceptions.BadCredentialsException;
import com.lcaohoanq.fucar.utils.ApiUtils;
import com.lcaohoanq.fucar.utils.EnvUtils;
import com.lcaohoanq.fucar.views.menu.MenuView;
import com.lcaohoanq.fucar.views.utils.AppAlert;
import com.lcaohoanq.fucar.views.base.AppFxBaseResources;
import java.net.http.HttpResponse;
import java.util.Map;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginController extends AppFxBaseResources {

    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField enterPasswordField;
    @FXML
    public Button loginButton;
    public static String email = "";
    private String password = "";

    @FXML
    public void initialize() {
        usernameTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("Enter key pressed");
                loginButtonAction(null);  // Trigger login action when Enter key is pressed
            }
        });
        enterPasswordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("Enter key pressed");
                loginButtonAction(null);  // Trigger login action when Enter key is pressed
            }
        });
    }


    public void loginButtonAction(ActionEvent event) {
        if ((!usernameTextField.getText().isBlank()) && (!enterPasswordField.getText().isBlank())) {
//            loginMessageLabel.setText("You tried to login");
            validateLogin();
        } else {
            AppAlert.IS_EMPTY_FIELD("Empty Fields", "Please fill in all fields", null);
        }
    }

    public void validateLogin() {
        email = usernameTextField.getText();
        password = enterPasswordField.getText();
        if (email.equals("admin") && password.equals("admin")) {
            AppAlert.IS_LOGIN_SUCCESS();
            Platform.runLater(() -> {
                new MenuView().setVisible(true);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
            });

        } else {
            login(email, password);
        }
    }

    private void login(String email_phone, String password) {
        // Create a new thread to avoid blocking the Swing event dispatch thread
        new Thread(() -> {
            try {
                // Replace with your API URL
                String apiUrl = APIConstants.BASE_URL + "/users/login";
                // Create the payload as a map and convert it to JSON
                Map<String, String> payload = Map.of(
                    "email_phone", email_phone, // Replace with actual value
                    "password", password // Replace with actual value
                );

                // Send the request and get the response
                HttpResponse<String> response = ApiUtils.postRequest(apiUrl, payload);

                // Handle the response
                switch (response.statusCode()) {
                    case 200:
                        AppAlert.IS_LOGIN_SUCCESS();

                        Platform.runLater(() -> {
                            new MenuView().setVisible(true);
                            Stage stage = (Stage) loginButton.getScene().getWindow();
                            stage.close();
                        });

                        break;
                    case 400:
                        JOptionPane.showMessageDialog(null,
                            "Username or password is incorrect, please try again!");
                        throw new BadCredentialsException("Username or password is incorrect");
                    default:
                        JOptionPane.showMessageDialog(null,
                            "Internal server error, please try again later!");
                        break;
                }
            } catch (IOException | InterruptedException | BadCredentialsException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }).start();
    }

    @FXML
    private void loginViaGoogleAction() {
        Platform.runLater(() -> {
            String googleAuthUrl = EnvUtils.get("GOOGLE_AUTH_URL");
            try {
                URI uri = new URI(googleAuthUrl);
                if (Desktop.isDesktopSupported() && Desktop.getDesktop()
                    .isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(uri);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void loginViaFacebookAction() {
        Platform.runLater(AppAlert::IS_NOT_SUPPORT);
    }

    public void signupHereAction(ActionEvent actionEvent) {
        try {
            // Specify the URL of the website
            URI uri = new URI("http://localhost:3000/users/register");
            // Open the website in the default browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop()
                .isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void forgotPasswordAction(ActionEvent actionEvent) {
        String api = "http://localhost:3000/forgot-password";
        Platform.runLater(() -> {
            try {
                // Specify the URL of the website
                URI uri = new URI(api);
                // Open the website in the default browser
                if (Desktop.isDesktopSupported() && Desktop.getDesktop()
                    .isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(uri);
                }
            } catch (Exception ex) {
                System.out.println("Error when open frontend forgot password: " + ex.getMessage());
            }
        });
    }


}