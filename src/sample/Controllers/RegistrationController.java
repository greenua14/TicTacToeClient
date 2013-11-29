package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.InteractionWithServer;
import sample.LoadSomeForm;

public class RegistrationController {

    public Button regButton;
    public TextField login;
    public PasswordField password;
    public PasswordField confirmPassword;
    public TextField email;

    public void showAuthorizationForm(ActionEvent actionEvent) {
        new LoadSomeForm().load("FXML/authorization.fxml", "Авторизация");
        closeThis();
    }

    private void closeThis() {
        ((Stage) regButton.getScene().getWindow()).close();
    }

    public void SignUp(ActionEvent actionEvent) {
        String log = login.getText().trim();
        String pass = password.getText().trim();
        String pass2 = confirmPassword.getText().trim();
        String em = email.getText().trim();
        String s = checkInputDate(log, pass, pass2, em);

        if (!s.equals("")) {
            new LoadSomeForm().showErrorMessage(s);
        } else {
            InteractionWithServer server = new InteractionWithServer();
            if (server.checkLoginInBD(log)) {
                new LoadSomeForm().showErrorMessage("Логин занят");
            } else {
                InteractionWithServer server2 = new InteractionWithServer();
                server2.registration(log, pass, em);
                new LoadSomeForm().load("FXML/CreateOrConnectToGame.fxml", log);
                closeThis();
            }
        }
    }

    private String checkInputDate(String login, String password, String password2, String email) {
        String res = new String();
        if (!login.matches("[A-Za-z0-9]+") || (login.charAt(0) >= 48 && login.charAt(0) <= 58) || login.length() < 5) {
            res += "Логин - только англ. буквы или цифры \n     и первый символ не цифра \n" +
                    "       и не меньше 5 символов \n";
        }
        if (!password.matches("[A-Za-z0-9]+")) {
            res += "Пароль - только англ. буквы или цифры \n        и не меньше 5 символов \n";
        }
        if (!password.equals(password2)) {
            res += "Пароли не совпадают \n";
        }
        if (!email.matches("[A-Za-z0-9@\\.]+")) {
            res += "Неверный email-адрес \n";
        }
        return res;
    }
}
