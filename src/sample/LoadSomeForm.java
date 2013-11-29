package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.ErrorFormController;

import java.io.IOException;

public class LoadSomeForm {

    public void load(String res, String title){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(res));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showErrorMessage(String msg){
        load("FXML/errorForm.fxml", "error");
        ErrorFormController.textErrorID.setText(msg);
    }
}
