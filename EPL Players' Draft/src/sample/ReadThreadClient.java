package sample;

import javafx.application.Platform;

import java.io.IOException;
import java.util.List;

public class ReadThreadClient implements Runnable {
    private Client client;
    private Thread thr;
    private NetworkUtil networkUtil;

    public ReadThreadClient(NetworkUtil networkUtil, Client client) {
        this.networkUtil = networkUtil;
        this.client=client;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o instanceof Message) {
                    Message obj = (Message) o;
                    String instruction=obj.getInstruction();
                    Player player=obj.getPlayer();
                    if(instruction.equalsIgnoreCase("SuccessfullLogIn")){
                        //System.out.println(" Successful log in from read thread");
                        //client.getLogin().setLogInStatus(true);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    client.getOpen().showCoverPage();
                                }catch (Exception e){
                                    System.out.println(e);
                                }
                            }
                        });
                    }
                    else if(instruction.equalsIgnoreCase("UnSuccessfullLogIn")){
                        System.out.println(" UnSuccessful log in from read thread");
                        //client.getLogin().setLogInStatus(false);
                        //Thread.currentThread().notifyAll();
                    }
                    else if(instruction.equalsIgnoreCase("ClublogIn")){
                        // System.out.println(" ,In Read Thread client " + player.getName());
                        client.getClub().addPlayer(player);
                    }
                }
                else if(o instanceof MessageOfList){
                    MessageOfList obj = (MessageOfList) o;
                    String instruction=obj.getInstruction();
                    List<Player> list= obj.getPlayerList();
                    if(instruction.equalsIgnoreCase("SaleListUpdated")){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    client.getClub().setBuyablePlayers(list);
                                }catch (Exception e){
                                    System.out.println(e);
                                }
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
