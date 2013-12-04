package sample;

import generalClasses.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

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
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
    }

    public InteractionWithServer(int port) {
        config = new GetIpAndPortFromFile();
        try {
            client = new Socket(config.IP, port);
            outputStream = new ObjectOutputStream((client.getOutputStream()));
            inputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
    }

    public boolean authorization(String login, String password) {
        try {
            outputStream.writeObject(new AuthorizationUser(login, password));
            outputStream.flush();
            return (boolean) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
        return false;
    }

    public boolean checkLoginInBD(String login) {
        try {
            outputStream.writeObject(new UserLogin(login));
            outputStream.flush();
            return (boolean) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
        return false;
    }

    public void registration(String login, String password, String email) {
        try {
            outputStream.writeObject(new RegistrationUser(login, password, email));
            outputStream.flush();
        } catch (IOException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
    }

    public int createGame(GameInfo gameInfo) {
        try {
            outputStream.writeObject(gameInfo);
            outputStream.flush();
            return (int) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
        return 0;
    }

    public Object checkCreatedGames() {
        try {
            outputStream.writeObject("checkCreatedGames");
            outputStream.flush();
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
        return null;
    }

    public HashMap<String, String> connectToCreatedGame(String login, String picture) throws IOException {
        outputStream.writeObject(new ConnectOrDisconnectGameInfo(login, picture));
        outputStream.flush();
        try {
            return (HashMap<String, String>)inputStream.readObject();
        } catch (ClassNotFoundException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
        return null;
    }

    public void disconnectFromGame(String login){
        try {
            outputStream.writeObject(new ConnectOrDisconnectGameInfo(login));
            outputStream.flush();
        } catch (IOException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
    }

    public Step readStep(){
        try {
            return (Step) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
        return null;
    }
}
