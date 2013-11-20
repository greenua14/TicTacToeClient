package generalClasses;

import java.io.Serializable;

public class GameInfo implements Serializable{
    private String fatherLogin;
    private int playersCount;
    private int fieldSize;
    private int port;

    public GameInfo(){

    }

    public GameInfo(String fatherLogin, int playersCount, int fieldSize) {
        this.fatherLogin = fatherLogin;
        this.playersCount = playersCount;
        this.fieldSize = fieldSize;
    }

    public String getFatherLogin() {
        return fatherLogin;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
