package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private String Name;
    private int PlayerCount;
    private List<Player> clubPlayer;
    private List <Player> BuyablePlayers;
    private Client client;

    public void setName(String Name){
        this.Name=Name;
    }
    public String getName(){
        return Name;
    }
    public int getPlayerCount(){return PlayerCount;}
    public List<Player> getAllClubPlayer() {
        return clubPlayer;
    }
    public void setBuyablePlayers(List<Player> BuyablePlayersfromserver){
        BuyablePlayers= new ArrayList();
        for(Player p: BuyablePlayersfromserver){
            if(p.getClubName().equalsIgnoreCase(Name)){
            }
            else{
                BuyablePlayers.add(p);
            }
        }
    }
    public List<Player> getBuyablePlayers() {
        return BuyablePlayers;
    }

    Club(Client client) {
        this.client=client;
        this.Name=client.getClientName();
        PlayerCount = 0;
        clubPlayer = new ArrayList();
    }

    public List<Player> SearchPlayerByName(String Name){
        List<Player> data= new ArrayList();
        for(Player p: clubPlayer ){
            if(p.getName().equalsIgnoreCase(Name)){
                data.add(p);
            }
        }
        return data;
    }
    public List<Player> SearchPlayerByCountry(String countryName){
        List<Player> data= new ArrayList();
        for (Player p :clubPlayer){
            if (p.getCountry().equalsIgnoreCase(countryName)){
                data.add(p);
            }
        }
        return data;
    }
    public List<Player> SearchPlayerByPosition(String position){
        List<Player> data= new ArrayList<>();
        for(Player p:clubPlayer){
            if(p.getPosition().equalsIgnoreCase(position)){
                data.add(p);
            }
        }
        return data;
    }
    public List<Player> SearchPlayerBySalary( String s1, String s2){
        Double low= Double.parseDouble(s1);
        Double high= Double.parseDouble(s2);
        List<Player> data= new ArrayList<>();
        for( Player p: clubPlayer){
            if(p.getSalary()>= low && p.getSalary()<= high){
                data.add(p);
            }
        }
        return data;
    }
    public List<Player> ClubPlayerWithMaxAge(){
        List<Player> data= new ArrayList<>();
        int MaxAge=0;
        for(Player p: clubPlayer){
            if(p.getAge()>=MaxAge){
                MaxAge=p.getAge();
            }
        }
        for(Player p: clubPlayer){
            if(p.getAge()==MaxAge){
                data.add(p);
            }
        }
        return data;
    }

    public List<Player> ClubPlayerWithMaxHeight(){

        List<Player> data= new ArrayList<>();
        double MaxHeight=0;
        for(Player p: clubPlayer){
            if(p.getHeight()>=MaxHeight){
                MaxHeight=p.getHeight();
            }
        }
        for(Player p:clubPlayer){
            if(p.getHeight()==MaxHeight){
                data.add(p);
            }
        }
        return data;
    }

    public List<Player> ClubPlayerWithMaxSalary(){
        List<Player> data= new ArrayList();
        double MaxSalary=0;
        for(Player p: clubPlayer){
            if(p.getSalary()>=MaxSalary){
                MaxSalary=p.getSalary();
            }
        }
        for(Player p: clubPlayer){
            if(p.getSalary()==MaxSalary){
                data.add(p);
            }
        }
        return data;
    }

    public String ClubTotalSalary(){
        double TotalSalary=0;
        for(Player p: clubPlayer){
            TotalSalary+=p.getSalary();
        }
        TotalSalary*=52;
        String s= "Total salary = " + String.format("%.2f",TotalSalary);
        return s;
    }

    public ObservableList<String> CountryWisePlayerCount(){
        ObservableList<String > result= FXCollections.observableArrayList();
        List<String > AllCountry= new ArrayList();

        for( Player p: clubPlayer){
            boolean isAvailabe=false;
            for( String s : AllCountry){
                if(p.getCountry().equalsIgnoreCase(s)){
                    isAvailabe=true;
                    break;
                }
            }
            if(isAvailabe==false){
                AllCountry.add(p.getCountry());
            }
        }
        for(String s: AllCountry){
            int count=0;
            for( Player p: clubPlayer){
                if(p.getCountry().equalsIgnoreCase(s)){
                    count++;
                }
            }
            String string = s + " , " +"Number of Player is "+ count;
            result.add(string);
        }
        return result;
    }

    public void removePlayer(Player player) {
        clubPlayer.remove(player);
    }
    public void addPlayer(Player player) {
        clubPlayer.add(player);
    }
    public void removeFromBuyablePlayer(Player player) {
        BuyablePlayers.remove(player);
    }
}
