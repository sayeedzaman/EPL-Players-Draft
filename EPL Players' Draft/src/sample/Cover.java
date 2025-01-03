package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Locale;

public class Cover {
    private Client client;
    private Open main;
    private Stage stage;
    public void setMain(Open main) {
        this.main = main;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setClient(Client client) {
        this.client=client;
    }
    public Client getClient() {
        return client;
    }

    public void setClubNameLabel(String clubName) {
        clubNameLabel.setText(clubName.toUpperCase(Locale.ROOT));
    }

    private int state;

    @FXML
    private Label clubNameLabel;

    @FXML
    private TextField box1;

    @FXML
    private Text text1;

    @FXML
    private TextField box2;

    @FXML
    private Text text2;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button searchButton;

    @FXML
    private Pane pane2;

    @FXML
    private Text salaryText;

    @FXML
    private ListView<String> list;

    @FXML
    private GridPane grid;

    @FXML
    void SearchbyName(ActionEvent event) {
        state=1;
        text1.setText(" Enter a Player's Name");
        text1.setVisible(true);
        box1.clear();
        box1.setVisible(true);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(true);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
    }

    @FXML
    void SearchbyCountry(ActionEvent event) {
        state=2;
        text1.setText(" Enter a Country Name");
        text1.setVisible(true);
        box1.clear();
        box1.setVisible(true);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(true);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
    }

    @FXML
    void SearchbyPosition(ActionEvent event) {
        state=3;
        text1.setText(" Enter a Position");
        text1.setVisible(true);
        box1.clear();;
        box1.setVisible(true);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(true);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
    }

    @FXML
    void SearchbySalaryRange(ActionEvent event) {
        state=4;
        text1.setText(" Enter Lower Limit");
        text2.setText(" Enter Highest Limit");
        text1.setVisible(true);
        box1.clear();
        box2.clear();
        box1.setVisible(true);
        text2.setVisible(true);
        box2.setVisible(true);
        searchButton.setVisible(true);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
    }

    @FXML
    void PlayerWithMaxAge(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(false);
        scrollPane.setVisible(true);
        pane2.setVisible(false);
        List<Player> result=client.getClub().ClubPlayerWithMaxAge();
        showplayer(result);
    }

    @FXML
    void PlayerWithMaxheight(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        pane2.setVisible(false);
        searchButton.setVisible(false);
        scrollPane.setVisible(true);
        List<Player> result=client.getClub().ClubPlayerWithMaxHeight();
        showplayer(result);
    }

    @FXML
    void PlayerWithMaxSalary(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        pane2.setVisible(false);
        searchButton.setVisible(false);
        scrollPane.setVisible(true);
        List<Player> result=client.getClub().ClubPlayerWithMaxSalary();
        showplayer(result);
    }

    @FXML
    void CountrywisePlayer(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(false);
        salaryText.setVisible(false);
        scrollPane.setVisible(false);
        pane2.setVisible(true);
        list.setVisible(true);
        salaryText.setVisible(false);
        ObservableList<String> result = client.getClub().CountryWisePlayerCount();
        list.setItems(result);
    }

    @FXML
    public void AllPlayersOfClub(ActionEvent actionEvent) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        pane2.setVisible(false);
        searchButton.setVisible(false);
        scrollPane.setVisible(true);
        List<Player> result=client.getClub().getAllClubPlayer();
        showplayer(result);
    }

    @FXML
    void TotalClubSalary(ActionEvent event) {
        text1.setVisible(false);
        box1.setVisible(false);
        text2.setVisible(false);
        box2.setVisible(false);
        searchButton.setVisible(false);
        scrollPane.setVisible(false);
        pane2.setVisible(true);
        list.setVisible(false);
        salaryText.setVisible(true);
        salaryText.setText(client.getClub().ClubTotalSalary());
    }


    @FXML
    void buyPlayer(ActionEvent event) {

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BuyPlayers.fxml"));
            Parent root = loader.load();


            BuyPlayers controller = loader.getController();
            controller.setStage(stage);
            controller.setCover(this);
            controller.getBuyPlayerList();


            stage.setTitle("Transfer Buy Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch( Exception e){
            System.out.println("Exception in buy player in Homepage" + e);
        }
    }

    @FXML
    void sellPlayer(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SellPlayers.fxml"));
            Parent root = loader.load();


            SellPlayers controller = loader.getController();
            controller.setStage(stage);
            controller.setCover(this);
            controller.getCurrentPlayerList();


            stage.setTitle("Transfer Sell Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch( Exception e){
            System.out.println("Exception in sell player in Homepage" + e);
        }

    }



    @FXML
    void logOut(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Open.fxml"));
            Parent root = loader.load();


            Open  controller = loader.getController();
            controller.setStage(stage);


            stage.setTitle("Openning Page");
            stage.setScene(new Scene(root));
            stage.show();
        }catch ( Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void SearchPressed(ActionEvent actionEvent) {
        scrollPane.setVisible(true);

        pane2.setVisible(false);
        if(state==1){
            List<Player> result=client.getClub().SearchPlayerByName(box1.getText());
            showplayer(result);
        }
        else if( state==2){
            List<Player> result=client.getClub().SearchPlayerByCountry(box1.getText());
            showplayer(result);
        }
        else if( state==3){
            //System.out.println("aa");
            List<Player> result=client.getClub().SearchPlayerByPosition(box1.getText());
            showplayer(result);
        }
        else if( state==4){
            List<Player> result=client.getClub().SearchPlayerBySalary(box1.getText(),box2.getText());
            showplayer(result);
        }
    }

    private void showplayer(List<Player> playerlist){
        int column=1,row=1;
        try{
            column=0;
            for(Player p: playerlist){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ResultPlayer.fxml"));
                AnchorPane anchorpane=loader.load();

                ResultPlayer controller = loader.getController();
                controller.setData(p);

                grid.add(anchorpane,column,row++);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorpane,new Insets(10));
            }
        }catch(Exception e){
            System.out.println(" Exception in showing player loop" + e);
        }
    }
    @FXML
    public void refresh(){
        box1.setVisible(false);
        box1.clear();
        text1.setVisible(false);
        box2.setVisible(false);
        box2.clear();
        text2.setVisible(false);
        searchButton.setVisible(false);
        pane2.setVisible(false);
        scrollPane.setVisible(false);
        try{
            main.showCoverPage();
        }catch( Exception e){
            System.out.println("Exception in Refreshpressed in Cover Page" + e );
        }
    }


}
