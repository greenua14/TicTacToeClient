package sample.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class ErrorFormController implements Initializable{
    public static Label textErrorID;
    public static AnchorPane mainForm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainForm.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    ((Stage) textErrorID.getScene().getWindow()).close();
                }
            }
        });
    }
    //public static Button closeButton;

    /*public void closeErrDialog(ActionEvent actionEvent) {
        ((Stage) textErrorID.getScene().getWindow()).close();
    }*/



}
