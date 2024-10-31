package com.lcaohoanq.fucar;

import com.lcaohoanq.fucar.constants.ResourcePaths;
import com.lcaohoanq.fucar.utils.AudioUtils;
import com.lcaohoanq.fucar.utils.LogsUtils;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 650);
        stage.setTitle("FUCarRentingSystem");
        stage.initStyle(StageStyle.DECORATED); // Hide the window's title bar (close, minimize, maximize buttons)
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        LogsUtils.ensureLogsFolderExists();

        AudioUtils.getInstance().playAudio(Main.class.getResourceAsStream(
            ResourcePaths.URL_INTRO));

        launch();
    }
}