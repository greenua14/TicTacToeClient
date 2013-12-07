package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.InteractionWithServer;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationController implements Initializable{
    public Button signInButton;
    public TextField login;
    public PasswordField password;
    public AnchorPane mainForm;


    public void showRegistrationForm(ActionEvent actionEvent) throws Exception {
        new LoadSomeForm().load("FXML/Registration.fxml", "Регистрация");
        closeThis();
    }

    private void closeThis() {
        ((Stage) signInButton.getScene().getWindow()).close();
    }

    public void signIn(ActionEvent actionEvent) {
        String log = login.getText().trim();
        String pass = password.getText().trim();

        if (!checkInputData(log, pass)) {
            new LoadSomeForm().showErrorMessage("Логин - только англ. буквы и цифры \n      и первый символ не цифра \n" +
                    "Пароль - только англ. буквы и цифры \n");
        } else {
            InteractionWithServer server = new InteractionWithServer();
            if (server.authorization(log, pass)) {
                new LoadSomeForm().load("FXML/CreateOrConnectToGame.fxml", log);
                closeThis();
            } else {
                new LoadSomeForm().showErrorMessage("Неверный логин или пароль");
            }
        }
    }

    private boolean checkInputData(String log, String pass) {
        if (!log.matches("[A-Za-z0-9]+") || (log.charAt(0) >= 48 && log.charAt(0) <= 58) || !pass.matches("[A-Za-z0-9]+")) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainForm.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    signIn(new ActionEvent());
                }
            }
        });
    }
}
