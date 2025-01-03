package sample;

import java.io.Serializable;

public class Message implements Serializable {
    private String instruction;
    private String client;
    private Player player;

    public Message() {
        client=null;
    }
    public String getInstruction() {
        return instruction;
    }
    public void setInstruction(String from) {
        this.instruction = from;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player to) {
        this.player = to;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClient() {
        return client;
    }
}
