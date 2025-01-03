package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ResultPlayer {
    private Player player;
    private int state=0;
    private Cover cover;
    public void setCover(Cover h) {
        this.cover = h;
    }

    @FXML
    private Text name;

    @FXML
    private Text country;

    @FXML
    private Text age;

    @FXML
    private Text salary;

    @FXML
    private Text position;

    @FXML
    private Text height;

    @FXML
    private Text number;

    @FXML
    private Button transfer;

    @FXML
    void transfer(ActionEvent event) {
        if(state==1){
            cover.getClient().getClub().removePlayer(player);
            NetworkUtil nu = cover.getClient().getNetworkUtil();
            Message txt= new Message();
            txt.setInstruction("SaleRequest");
            txt.setPlayer(player);
            try{
                nu.write(txt);
            }catch(Exception e){
                System.out.println("Exception in writting to server , in ShowPlayer ,transfer sale " + e);
            }
           cover.sellPlayer(event);
        }
        else if(state==2){
            Player newPlayer= new Player(player,cover.getClient().getClientName());
            cover.getClient().getClub().removeFromBuyablePlayer(player);
            cover.getClient().getClub().addPlayer(newPlayer);
            NetworkUtil nu = cover.getClient().getNetworkUtil();
            Message txt= new Message();
            txt.setInstruction("BuyRequest");
            txt.setClient(cover.getClient().getClientName());
            txt.setPlayer(player);
            System.out.println(player.getClubName() + newPlayer.getClubName());
            try{
                nu.write(txt);
            }catch(Exception e){
                System.out.println("Exception in writting to server , in ShowPlayer ,transfer sale " + e);
            }
            cover.buyPlayer(event);
        }
    }

    public void setData( Player p){
        this.player=p;
        String s;
        name.setText("Name : " + p.getName());
        country.setText("Country : " + p.getCountry());
        s=""+p.getAge();
        age.setText("Age : " + s);
        s=""+p.getHeight();
        height.setText("Height : " + s);
        position.setText("Position : " + p.getPosition());
        s=""+p.getNumber();
        number.setText("Number : " + s);
        s=""+p.getSalary();
        salary.setText("Salary : " + s);
        transfer.setVisible(false);
    }

    public void setDataforSale(Player p) {
        this.player=p;
        String s;
        name.setText("Name : " + p.getName());
        country.setText("Country : " + p.getCountry());
        s=""+p.getAge();
        age.setText("Age : " + s);
        s=""+p.getHeight();
        height.setText("Height : " + s);
        position.setText("Position : " + p.getPosition());
        s=""+p.getNumber();
        number.setText("Number : " + s);
        s=""+p.getSalary();
        salary.setText("Salary : " + s);
        transfer.setText("Sale");
        transfer.setVisible(true);
        state=1;
    }

    public void setDataforBuy(Player p) {
        this.player=p;
        String s;
        name.setText("Name : " + p.getName());
        country.setText("Country : " + p.getCountry());
        s=""+p.getAge();
        age.setText("Age : " + s);
        s=""+p.getHeight();
        height.setText("Height : " + s);
        position.setText("Position : " + p.getPosition());
        s=""+p.getNumber();
        number.setText("Number : " + s);
        s=""+p.getSalary();
        salary.setText("Salary : " + s);
        transfer.setText("Buy");
        transfer.setVisible(true);
        state=2;
    }
}
