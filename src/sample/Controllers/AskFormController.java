package sample.Controllers;


import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.InteractionWithServer;
import sample.LoadSomeForm;

public class AskFormController {
    static public Label message;

    public void doSome(ActionEvent actionEvent) {
        new InteractionWithServer(GameController.PORT).disconnectFromGame(GameController.login);
        new LoadSomeForm().load("FXML/CreateOrConnectToGame.fxml", GameController.login);
        closeThis();
        GameController.stage.close();
    }

    private void closeThis(){
        ((Stage) message.getScene().getWindow()).close();
    }

    public void cancel(ActionEvent actionEvent) {
        closeThis();
    }
}
