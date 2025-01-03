package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private String Name;
    private String Country;
    private int Age;
    private double Height;
    private String ClubName;
    private String Position;
    private int Number;
    private double Salary;


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String clubName) {
        ClubName = clubName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public Player(Player player, String clientName) {
        this.Name= player.getName();
        this.Country=player.getCountry();
        this.Age=player.getAge();
        this.Height=player.getHeight();
        this.ClubName=clientName;
        this.Position=player.getPosition();
        this.Number=player.getNumber();
        this.Salary=player.getSalary();
    }
    public Player(){

    }
}
