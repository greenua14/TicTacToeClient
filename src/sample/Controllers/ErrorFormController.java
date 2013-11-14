package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ErrorFormController{
    public static Label textErrorID;
    public  AnchorPane ErrorFormID;
    public  Button buttonExitErrorID;

    public void exitErrorForm(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage =  (Stage) textErrorID.getScene().getWindow();
        stage.close();
    }
}
