package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;

public class Open {
    private Client client;
    private Stage stage;
    public Client getClient() {
        return client;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextField OpenningBox;

    @FXML
    public void OpenAttempt(ActionEvent event) {
        String clubName=OpenningBox.getText();
        String serverAddress = "127.0.0.1";
        int serverPort = 55555;
        client = new Client(serverAddress, serverPort,clubName,this);
    }

    public void showCoverPage() throws Exception{
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Cover.fxml"));
            Parent root = loader.load();

            // Loading the controller
            Cover controller = loader.getController();
            controller.setMain(this);
            controller.setStage(stage);
            controller.setClient(client);
            controller.setClubNameLabel(client.getClub().getName());

            // Set the primary stage
            stage.setTitle("Cover");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Exception in open class , Successfull LogIn method" + e);
        }
    }

}
