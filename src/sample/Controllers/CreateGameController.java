package sample.Controllers;

import generalClasses.GameInfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import sample.InteractionWithServer;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateGameController implements Initializable {
    public Hyperlink backLink;
    public Button createGameButton;
    public ChoiceBox<String> playersCount;
    public ChoiceBox<String> fieldSize;
    private Stage stage = null;

    public void goBack(ActionEvent actionEvent) {
        closeThis();
        new LoadSomeForm().load("FXML/CreateOrConnectToGame.fxml", stage.getTitle());
    }

    private void closeThis() {
        stage = (Stage) createGameButton.getScene().getWindow();
        stage.close();
    }

    public void createGame(ActionEvent actionEvent) {
        Object fs = fieldSize.getValue();
        Object pc = playersCount.getValue();
        if (fs == null || pc == null) {
            new LoadSomeForm().load("FXML/errorForm.fxml", "Error");
            ErrorFormController.textErrorID.setText("Выберите количество игроков и размер поля");
        } else {
            InteractionWithServer server = new InteractionWithServer();
            GameController.peopleCount = Integer.parseInt(pc.toString());
            GameController.boxCount = Integer.parseInt(fs.toString().substring(0, 2));
            closeThis();
            new LoadSomeForm().load("FXML/Game.fxml", stage.getTitle());
            try {
                GameController.PORT.setText(String.valueOf(server.createGame(new GameInfo(stage.getTitle(), GameController.peopleCount, GameController.boxCount))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playersCount.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                switch (s2) {
                    case "2": {
                        fieldSize.setItems(FXCollections.observableArrayList("20x20", "25x25", "30x30"));
                        break;
                    }
                    case "3": {
                        fieldSize.setItems(FXCollections.observableArrayList("25x25", "30x30"));
                        break;
                    }
                    case "4": {
                        fieldSize.setItems(FXCollections.observableArrayList("25x25", "30x30"));
                        break;
                    }
                    case "5": {
                        fieldSize.setItems(FXCollections.observableArrayList("30x30"));
                        break;
                    }
                }
            }
        });
    }


}
