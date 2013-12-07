package generalClasses;

import java.io.Serializable;
import java.net.Socket;

public class ConnectOrDisconnectGameInfo implements Serializable{
    private String login;
    private String picture;
    private boolean flag;
    private Socket personalSocket;

    public ConnectOrDisconnectGameInfo(String login, String picture) {
        this.login = login;
        this.picture = picture;
        flag = true;
        personalSocket = null;
    }

    public ConnectOrDisconnectGameInfo(String login){
        this.login = login;
        flag = false;
    }

    public Socket getPersonalSocket() {
        return personalSocket;
    }

    public void setPersonalSocket(Socket personalSocket) {
        this.personalSocket = personalSocket;
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
