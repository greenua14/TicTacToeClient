package generalClasses;

import java.io.Serializable;
import java.util.HashMap;

public class GameInfo implements Serializable{
    private String fatherLogin;
    private int playersCountMax;
    private int playersCountNow;
    private int fieldSize;
    private StringBuffer pictures;
    private int PORT;
    private HashMap<String,String> playersList;

    public GameInfo(){
        pictures = new StringBuffer("");
        playersList = new HashMap<>();
    }

    public GameInfo(String fatherLogin, int playersCount, int fieldSize, String picture) {
        playersList = new HashMap<>();
        pictures = new StringBuffer();
        playersCountNow = 1;
        this.fatherLogin = fatherLogin;
        this.playersCountMax = playersCount;
        this.fieldSize = fieldSize;
        pictures.append(picture);
    }

    public String getPictures() {
        return pictures.toString();
    }

    public void setPictures(String picture) {
        this.pictures.append(picture);
    }

    public void deletePictures(String pic){
        int i = pictures.indexOf(pic);
        pictures.delete(i,i+7);
    }

    public int getPlayersCountNow() {
        return playersCountNow;
    }

    public String getFatherLogin() {
        return fatherLogin;
    }

    public void setFatherLogin(String fatherLogin) {
        this.fatherLogin = fatherLogin;
    }

    public void setPlayersCountMax(int playersCountMax) {
        this.playersCountMax = playersCountMax;
    }

    public void setPlayersCountNow(int playersCountNow) {
        this.playersCountNow = playersCountNow;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public void setPlayersList(HashMap<String, String> playersList) {
        this.playersList = playersList;
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
