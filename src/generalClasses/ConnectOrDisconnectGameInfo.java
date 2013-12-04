package generalClasses;

import java.io.Serializable;

public class ConnectOrDisconnectGameInfo implements Serializable{
    private String login;
    private String picture;
    private boolean flag;

    public ConnectOrDisconnectGameInfo(String login, String picture) {
        this.login = login;
        this.picture = picture;
        flag = true;
    }

    public ConnectOrDisconnectGameInfo(String login){
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPicture() {
        return picture;
    }

    public boolean isFlag() {
        return flag;
    }
}
