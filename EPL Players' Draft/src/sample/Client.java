package sample;

public class Client {
    private String clientName;
    private Open open;
    private NetworkUtil networkUtil;
    private Club club;

    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }
    public String getClientName() {
        return clientName;
    }
    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
    public Open getOpen() {
        return open;
    }

    public Client(String serverAddress, int serverPort, String clientName, Open open) {
        try {
            this.open = open;
            this.clientName=clientName;
            club= new Club(this);
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(clientName);
            new ReadThreadClient(networkUtil, this);
        } catch (Exception e) {
            System.out.println(" Exception in ClientSocket" + e);
        }
    }
}
