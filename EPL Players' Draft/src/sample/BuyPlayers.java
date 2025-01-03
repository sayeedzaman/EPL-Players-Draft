package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BuyPlayers {
    private Cover cover;
    private Stage stage;
    private List<Player> BuyPlayerlist;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    @FXML
    private GridPane grid;



    public void getBuyPlayerList(){
        this.BuyPlayerlist= new ArrayList();
        BuyPlayerlist = cover.getClient().getClub().getBuyablePlayers();
        grid.getChildren().clear();
        int col = 0, row = 0;
        try{
            col=0;
            for(Player p: BuyPlayerlist){

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ResultPlayer.fxml"));
                AnchorPane anchorpane=loader.load();

                ResultPlayer controller = loader.getController();
                controller.setDataforBuy(p);
                controller.setCover(cover);

                grid.add(anchorpane,col,row++);

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
    void Back(ActionEvent event) {

        cover.refresh();
    }

}
