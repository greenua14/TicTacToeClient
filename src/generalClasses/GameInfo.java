package generalClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameInfo implements Serializable{
    private String fatherLogin;
    private int playersCountMax;
    private int playersCountNow;
    private int fieldSize;
    private List<String> pictures;
    private HashMap<String,String> playersList;

    public GameInfo(){

    }

    public GameInfo(String fatherLogin, int playersCount, int fieldSize, String picture) {
        this.fatherLogin = fatherLogin;
        this.playersCountMax = playersCount;
        this.fieldSize = fieldSize;
        pictures = new ArrayList<>();
        pictures.add(picture);
    }

    public List<String> getPictures() {
        return pictures;
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

    public void setPlayersCountNow(int playersCountNow) {
        this.playersCountNow = playersCountNow;
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
}
