package sample;

import generalClasses.AuthorizationUser;
import generalClasses.RegistrationUser;
import generalClasses.UserLogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InteractionWithServer {
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    private Socket client = null;

    public InteractionWithServer() {
        GetIpAndPortFromFile config = new GetIpAndPortFromFile();
        try {
            client = new Socket(config.IP, config.PORT);
            outputStream = new ObjectOutputStream((client.getOutputStream()));
            inputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authorization(String login, String password) {
        try {
            outputStream.writeObject(new AuthorizationUser(login, password));
            outputStream.flush();
            return (boolean) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLoginInBD(String login) {
        try {
            outputStream.writeObject(new UserLogin(login));
            outputStream.flush();
            return (boolean) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registration(String login, String password, String email) {
        try {
            outputStream.writeObject(new RegistrationUser(login, password, email));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
