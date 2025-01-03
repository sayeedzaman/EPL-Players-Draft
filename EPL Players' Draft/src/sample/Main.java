package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        showOpenningPage();
    }
    public void  showOpenningPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Open.fxml"));
        Parent root = loader.load();

        // Loading the controller
        Open controller = loader.getController();
        controller.setStage(stage);

        // Set the primary stage
        stage.setTitle("Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }



    public static void main(String[] args) {

        launch(args);
    }
}
