package generalClasses;

import java.io.Serializable;

public class UserLogin implements Serializable{
    private String login;

    public UserLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}
