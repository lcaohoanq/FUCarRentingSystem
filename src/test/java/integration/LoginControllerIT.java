package integration;

import com.lcaohoanq.fucar.controllers.LoginController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@Disabled("Disabled until we have a way to run GUI tests")
public class LoginControllerIT extends ApplicationTest {

    private LoginController loginController;
    private TextField usernameTextField;
    private PasswordField enterPasswordField;
    private Button loginButton;

    @Override
    public void start(Stage stage) {
        loginController = new LoginController();
        usernameTextField = new TextField();
        enterPasswordField = new PasswordField();
        loginButton = new Button("Login");

        loginController.usernameTextField = usernameTextField;
        loginController.enterPasswordField = enterPasswordField;
        loginController.loginButton = loginButton;

        VBox root = new VBox(10, usernameTextField, enterPasswordField, loginButton);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.show();

        loginButton.setOnAction(event -> loginController.loginButtonAction(event));
    }

    @Test
    public void testSuccessfulLogin() {
        // Arrange
        clickOn(usernameTextField).write("admin");
        clickOn(enterPasswordField).write("admin");

        // Act
        clickOn(loginButton);

        // Assert
        // You need to implement a way to check if login was successful
        // For example, you could add a label to your LoginController that shows "Login Successful"
        // verifyThat("#successLabel", hasText("Login Successful"));
    }

    @Test
    public void testFailedLogin() {
        // Arrange
        clickOn(usernameTextField).write("wronguser");
        clickOn(enterPasswordField).write("wrongpass");

        // Act
        clickOn(loginButton);

        // Assert
        // You need to implement a way to check if login failed
        // For example, you could add a label to your LoginController that shows error messages
        // verifyThat("#errorLabel", hasText("Invalid username or password"));
    }

    @Test
    public void testEmptyFields() {
        // Act
        clickOn(loginButton);

        // Assert
        // You need to implement a way to check if empty fields are handled
        // For example, you could add a label to your LoginController that shows warning messages
        // verifyThat("#warningLabel", hasText("Please enter username and password"));
    }
}