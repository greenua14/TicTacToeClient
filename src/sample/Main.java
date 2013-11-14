package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML/AuthorizationOrRegistration.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /*GetIpAndPortFromFile config = new GetIpAndPortFromFile();
        Socket client = null;
        client = new Socket(config.IP, config.PORT);

        ObjectOutputStream outputStream = new ObjectOutputStream((client.getOutputStream()));
        ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
        Object obj = new String("hello from client");
        outputStream.writeObject(obj);
        outputStream.flush();


        System.out.println(inputStream.readObject());*/
        launch(args);

    }


}
