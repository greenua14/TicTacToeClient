package generalClasses;

import java.io.Serializable;

public class ConnectToCreatedGameInfo implements Serializable{
    private String login;
    private String picture;

    public ConnectToCreatedGameInfo(String login, String picture) {
        this.login = login;
        this.picture = picture;
    }

    public String getLogin() {
        return login;
    }

    public String getPicture() {
        return picture;
    }
}
