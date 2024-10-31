package com.lcaohoanq.fucar.layouts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import com.lcaohoanq.fucar.layouts.Navigable;

public class AdminMainController implements Initializable, Navigable {

    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Set tours_home_page as the default content when the app starts
            setContent("home", contentArea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateHome() throws IOException {
        navigateHome(contentArea);
    }

    @FXML
    private void navigateMyProfile() throws IOException {
        navigateMyProfile(contentArea);
    }

    @FXML
    private void navigateTour() throws IOException {
        navigateTourManagement(contentArea);
    }

    @FXML
    private void navigateBooking() throws IOException {
        navigateBooking(contentArea);
    }

    @FXML
    private void navigateKoi() throws IOException {
        navigateKoi(contentArea);
    }

    @FXML
    private void navigateStaff() throws IOException {
        navigateStaff(contentArea);
    }

    @FXML
    private void navigateSetting() throws IOException {
        navigateSetting(contentArea);
    }

    @FXML
    private void navigateLogout() throws IOException {
        navigateLogout(contentArea);
    }

    @FXML
    private void navigateFarm() throws IOException {
        navigateFarm(contentArea);
    }

}
