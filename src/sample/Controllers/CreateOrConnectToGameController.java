package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateOrConnectToGameController implements Initializable{
    public Button createGameButton;
    public ScrollPane allGames;
    private Stage stage = null;

    public void logOut(ActionEvent actionEvent) {
        new LoadSomeForm().load("FXML/Authorization.fxml", "Авторизация");
        closeThis();
    }

    private void closeThis() {
        stage = ((Stage)createGameButton.getScene().getWindow());
        stage.close();
    }

    public void createNewGame(ActionEvent actionEvent) {
        closeThis();
        new LoadSomeForm().load("FXML/CreateGame.fxml", stage.getTitle());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
