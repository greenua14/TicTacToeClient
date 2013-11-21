package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateOrConnectToGameController implements Initializable {
    public Button createGameButton;
    public TableView createdGamesTable;
    private Stage stage = null;

    public void logOut(ActionEvent actionEvent) {
        new LoadSomeForm().load("FXML/Authorization.fxml", "Авторизация");
        closeThis();
    }

    private void closeThis() {
        stage = ((Stage) createGameButton.getScene().getWindow());
        stage.close();
    }

    public void createNewGame(ActionEvent actionEvent) {
        closeThis();
        new LoadSomeForm().load("FXML/CreateGame.fxml", stage.getTitle());
    }

    private void setDifParameters(TableColumn column, int width) {
        column.setResizable(false);
        column.setPrefWidth(width);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn number = new TableColumn("№");
        TableColumn fatherName = new TableColumn("Создал");
        TableColumn playersCountMax = new TableColumn("Макс. игроков");
        TableColumn playersCount = new TableColumn("Кол-во игроков");
        TableColumn fieldSize = new TableColumn("Размер поля");
        setDifParameters(number, 60);
        setDifParameters(fatherName, 200);
        setDifParameters(playersCount, 120);
        setDifParameters(playersCountMax, 120);
        setDifParameters(fieldSize, 100);
        createdGamesTable.getColumns().addAll(number, fatherName, playersCount, playersCountMax, fieldSize);
    }
}
