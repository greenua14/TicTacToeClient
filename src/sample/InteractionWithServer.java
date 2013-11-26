package sample;

import generalClasses.AuthorizationUser;
import generalClasses.GameInfo;
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
    public GetIpAndPortFromFile config;

    public InteractionWithServer() {
        config = new GetIpAndPortFromFile();
        try {
            client = new Socket(config.IP, config.PORT);
            outputStream = new ObjectOutputStream((client.getOutputStream()));
            inputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
        }
    }

    public InteractionWithServer(int port) {
        config = new GetIpAndPortFromFile();
        try {
            client = new Socket(config.IP, port);
            outputStream = new ObjectOutputStream((client.getOutputStream()));
            inputStream = new ObjectInputStream(client.getInputStream());
            System.out.println("InteractionWithServer connect gj");
        } catch (IOException e) {
        }
    }

    public boolean authorization(String login, String password) {
        try {
            outputStream.writeObject(new AuthorizationUser(login, password));
            outputStream.flush();
            return (boolean) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return false;
    }

    public boolean checkLoginInBD(String login) {
        try {
            outputStream.writeObject(new UserLogin(login));
            outputStream.flush();
            return (boolean) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return false;
    }

    public void registration(String login, String password, String email) {
        try {
            outputStream.writeObject(new RegistrationUser(login, password, email));
            outputStream.flush();
        } catch (IOException e) {
        }
    }

    public int createGame(GameInfo gameInfo) {
        try {
            outputStream.writeObject(gameInfo);
            outputStream.flush();
            return (int)inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return 0;
    }

    public Object checkCreatedGames(){
        try {
            outputStream.writeObject("checkCreatedGames");
            outputStream.flush();
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return null;
    }
}
