package pl.ksr.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class StageController {

    public static Stage applicationStage;




    private static void prepareStage(String filePath, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(filePath));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        applicationStage.setScene(scene);
        applicationStage.setTitle(title);
        applicationStage.show();
    }


    public static void buildStage(Stage stage, String filePath,
                                  String title) throws IOException {
        applicationStage = stage;
        prepareStage(filePath, title);
    }


    public static void loadStage(String filePath, String title) throws IOException {
        applicationStage = new Stage();
        prepareStage(filePath, title);
    }


    public static void reloadStage(String filePath, String title) throws IOException {
        applicationStage.close();
        loadStage(filePath, title);
    }

}

