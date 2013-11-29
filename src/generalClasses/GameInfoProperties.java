package generalClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

public class GameInfoProperties {
    private SimpleStringProperty fatherLogin;
    private SimpleIntegerProperty playersCountMax;
    private SimpleIntegerProperty playersCountNow;
    private SimpleIntegerProperty fieldSize;
    private SimpleStringProperty pictures;
    private HashMap<String,String> playersList;
    private SimpleIntegerProperty PORT;


    public GameInfoProperties(GameInfo info) {
        playersCountNow = new SimpleIntegerProperty(info.getPlayersCountNow());
        this.fatherLogin = new SimpleStringProperty(info.getFatherLogin());
        this.playersCountMax = new SimpleIntegerProperty(info.getPlayersCountMax());
        this.fieldSize = new SimpleIntegerProperty(info.getFieldSize());
        this.pictures = new SimpleStringProperty(info.getPictures());
        this.playersList = info.getPlayersList();
        this.PORT = new SimpleIntegerProperty(info.getPORT());
    }

    public String getPictures() {
        return pictures.get();
    }

    public SimpleStringProperty picturesProperty() {
        return pictures;
    }

    public int getPORT() {
        return PORT.get();
    }

    public SimpleIntegerProperty PORTProperty() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT.set(PORT);
    }

    public int getPlayersCountNow() {
        return playersCountNow.get();
    }

    public String getFatherLogin() {
        return fatherLogin.get();
    }

    public int getPlayersCountMax() {
        return playersCountMax.get();
    }

    public int getFieldSize() {
        return fieldSize.get();
    }

    public void setPlayersCountNow(int playersCountNow) {
        this.playersCountNow.set(playersCountNow);
    }

    public void addToPlayersList(String login, String picture){
        playersList.put(login, picture);
    }

    public void deleteFromPlayersList(String login){
        playersList.remove(login);
    }

    public HashMap getPlayersList(){
        return playersList;
    }
}
