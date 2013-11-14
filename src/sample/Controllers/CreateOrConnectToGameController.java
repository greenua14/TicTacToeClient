package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import sample.LoadSomeForm;

public class CreateOrConnectToGameController {


    public Button createGameButton;
    public ScrollPane allGames;
    private Stage stage = (Stage) createGameButton.getScene().getWindow();

    public void logOut(ActionEvent actionEvent) {
        new LoadSomeForm().load("FXML/Authorization.fxml", "Авторизация");
        closeThis();
    }

    private void closeThis(){
        stage.close();
    }

    public void createNewGame(ActionEvent actionEvent) {
        new LoadSomeForm().load("FXML/CreateGame.fxml", stage.getTitle());
        closeThis();
    }
}
