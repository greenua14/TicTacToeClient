package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.LoadSomeForm;

public class AuthorizationOrRegistrationController {

    public Button authorizationButton;
    public Button registrationButton;

    public void gotoAuthorization(ActionEvent actionEvent) {
        try {
            new LoadSomeForm().load("FXML/authorization.fxml", "Авторизация");
            closeThis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeThis(){
        ((Stage) authorizationButton.getScene().getWindow()).close();
    }

    public void gotoRegistration(ActionEvent actionEvent){
        try {
            new LoadSomeForm().load("FXML/Registration.fxml", "Регистрация");
            closeThis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
