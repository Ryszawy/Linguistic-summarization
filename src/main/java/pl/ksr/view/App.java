package pl.ksr.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("appView.fxml"));
        stage.setResizable(false);
        StageController.buildStage(stage, "/pl/ksr/view/appView.fxml", "Database Summarizator");
    }

    public static void main(String[] args) {
        launch();
    }
}