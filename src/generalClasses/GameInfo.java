package generalClasses;

import java.io.Serializable;
import java.util.HashMap;

public class GameInfo implements Serializable{
    private String fatherLogin;
    private int playersCountMax;
    private int playersCountNow;
    private int fieldSize;
    private String pictures;
    private int PORT;
    private HashMap<String,String> playersList;

    public GameInfo(){

    }

    public GameInfo(String fatherLogin, int playersCount, int fieldSize, String picture) {
        playersList = new HashMap<>();
        playersCountNow = 1;
        this.fatherLogin = fatherLogin;
        this.playersCountMax = playersCount;
        this.fieldSize = fieldSize;
        pictures = picture;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures += pictures;
    }

    public int getPlayersCountNow() {
        return playersCountNow;
    }

    public String getFatherLogin() {
        return fatherLogin;
    }

    public int getPlayersCountMax() {
        return playersCountMax;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void addPlayersCountNow() {
        this.playersCountNow += 1;
    }

    public void subtractPlayersCountNow(){
        this.playersCountNow -= 1;
    }

    public void addToPlayersList(String login, String picture){
        playersList.put(login, picture);
    }

    public void deleteFromPlayersList(String login){
        playersList.remove(login);
    }

    public HashMap<String,String> getPlayersList(){
        return playersList;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }
}
