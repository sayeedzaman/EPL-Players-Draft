package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private HashMap<String, NetworkUtil> clientMap = new HashMap<>();;
    private List<Player> AllPlayerList;
    private  List<Player> SalablePlayerList = new ArrayList<>();
    private List<String> ClubList= new ArrayList<String>();

    public HashMap<String, NetworkUtil> getClientMap (){
        return clientMap;
    }
    public List<Player> getSalablePlayerList(){
        return SalablePlayerList;
    }
    public void addToSellList (Player p){
        AllPlayerList.remove(p);
        if(SalablePlayerList.contains(p)==false){
            SalablePlayerList.add(p);
        }
    }
    public void removeFromSellList(Player player, String client) {
        for (int i=0; i<SalablePlayerList.size();i++ ) {
            if (SalablePlayerList.get(i).getClubName().equalsIgnoreCase(player.getClubName())){
                SalablePlayerList.remove(i);
            }
        }
            Player newPlayer = new Player(player, client);
            AllPlayerList.add(newPlayer);

        for(Player p: SalablePlayerList){
            System.out.println(p.getName()+p.getClubName()+client);
        }
    }

    Server() {
        AllPlayerList=  new ArrayList<Player>();
        try{
            AllPlayerList = File.readFromFile();
        } catch (Exception e){
            System.out.println("Exception in reading file" + e);
        }
        ClubListCreate();
        try {
            serverSocket = new ServerSocket(55555);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }


    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        String clientName = (String) networkUtil.read();
        boolean flag=false;
        for( String s : ClubList){
            if(s.equalsIgnoreCase(clientName)){
                clientName=s;
                flag=true;
            }
        }
        if(flag){
            clientMap.put(clientName, networkUtil);
            new ReadThreadServer(networkUtil,this);
            Message txt1 = new Message();
            txt1.setInstruction("SuccessfullLogIn");
            txt1.setPlayer(null);
            networkUtil.write(txt1);
            for(Player player : AllPlayerList){
                if(clientName.equalsIgnoreCase(player.getClubName())) {
                    Message txt = new Message();
                    txt.setInstruction("ClubLogIn");
                    txt.setPlayer(player);
                    networkUtil.write(txt);
                }
            }
            MessageOfList txt= new MessageOfList();
            txt.setInstruction("SaleListUpdated");
            txt.setPlayer(SalablePlayerList);
            try{
                networkUtil.write(txt);
            }catch(Exception e){
                System.out.println(" Exception in writting to client server" +" e");
            }
        }
        else{
            Message txt1 = new Message();
            txt1.setInstruction("UnSuccessfullLogIn");
            txt1.setPlayer(null);
            networkUtil.write(txt1);
            System.out.println("Wrong Club Name");
        }
    }

    private void ClubListCreate() {
        for(Player p: AllPlayerList){
            String club=p.getClubName();
            boolean flag=false;
            for(String s: ClubList){
                if(s.equalsIgnoreCase(club)){
                    flag=true;
                }
            }
            if(flag==false){
                ClubList.add(club);
            }
        }
    }
    public static void main(String args[]) {
        Server server = new Server();
    }
}
