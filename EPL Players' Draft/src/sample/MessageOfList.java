package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessageOfList implements Serializable {
    private String instruction;
    private List<Player> list= new ArrayList();

    public MessageOfList() {
    }

    public String getInstruction() {
        return instruction;
    }
    public void setInstruction(String from) {
        this.instruction = from;
    }

    public List<Player> getPlayerList() {
        return list;
    }
    public void setPlayer(List<Player> list) {
        this.list = list;
    }
}
