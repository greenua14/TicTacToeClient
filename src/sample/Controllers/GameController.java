package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public GridPane gameField;
    public static String figureStyle;
    public static int boxCount;
    public static int peopleCount;
    public static int PORT;
    public static String login;
    public Button startGameButton;
    public Pane playersTable;
    public Pane lostGameLink;
    private int playersCountInTable = 0;
    public static Stage stage;

    public static boolean makeStep() {
        return true;
    }

    private Pane createPane(int i, int j) {
        final Pane pane = new Pane();
        pane.setId(j + "-" + i);
        pane.setStyle("-fx-cursor: hand");
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(pane.getId());
            }
        });
        return pane;
    }

    private void createGameField(int n) {
        for (int i = 0; i < n; i++) {
            gameField.getColumnConstraints().add(new ColumnConstraints(22));
            gameField.getRowConstraints().add(new RowConstraints(22));
            for (int j = 0; j < n; j++) {
                gameField.add(createPane(j, i), j, i);
            }
        }
    }

    private void createPlayersTable(int layoutY) {
        playersTable.setLayoutY(layoutY);
        playersTable.setLayoutX(14);
    }

    private void addPlayersToTable() {
        for (Map.Entry<String, String> entry : SelectPictureBeforeConnectController.playersList.entrySet()) {
            Pane pane = new Pane();
            pane.setId(entry.getKey());
            Label label = new Label(entry.getKey());
            label.setPrefWidth(100);
            label.setPrefHeight(20);
            label.setLayoutX(30);
            ImageView view = new ImageView("sample/FXML/css/images/" + entry.getValue());
            view.setFitWidth(20);
            view.setFitHeight(20);
            pane.getChildren().add(view);
            pane.getChildren().add(label);
            pane.setLayoutY(playersCountInTable);
            playersCountInTable += 35;
            playersTable.getChildren().add(pane);
        }
        lostGameLink.setLayoutY(playersCountInTable+20);
    }

    private void addPlayersToTable(String login, String picture) {
        Pane pane = new Pane();
        pane.setId(login);
        Label label = new Label(login);
        label.setPrefWidth(100);
        label.setPrefHeight(20);
        label.setLayoutX(30);
        ImageView view = new ImageView("sample/FXML/css/images/" + picture);
        view.setFitWidth(20);
        view.setFitHeight(20);
        pane.getChildren().add(view);
        pane.getChildren().add(label);
        pane.setLayoutY(playersCountInTable);
        playersTable.getChildren().add(pane);
        lostGameLink.setLayoutY(140);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (CreateGameController.gameInfo != null) {
            boxCount = CreateGameController.gameInfo.getFieldSize();
            peopleCount = CreateGameController.gameInfo.getPlayersCountMax();
            figureStyle = CreateGameController.gameInfo.getPictures();
            PORT = CreateGameController.gameInfo.getPORT();
            login = CreateGameController.gameInfo.getFatherLogin();
            startGameButton.setVisible(true);
            startGameButton.setDisable(true);
            createPlayersTable(100);
            addPlayersToTable(login, figureStyle);

        } else {
            boxCount = CreateOrConnectToGameController.infoForConnect.getFieldSize();
            figureStyle = CreateOrConnectToGameController.infoForConnect.getPictures();
            login = CreateOrConnectToGameController.infoForConnect.getFatherLogin();
            PORT = CreateOrConnectToGameController.infoForConnect.getPORT();
            createPlayersTable(20);
            addPlayersToTable();
        }
        createGameField(boxCount);
        gameField.setDisable(true);
        //new InteractionWithServer().readStep();
    }

    public void startGame(ActionEvent actionEvent) {

    }

    public void lostGame(ActionEvent actionEvent) {
        stage = (Stage) gameField.getScene().getWindow();
        new LoadSomeForm().load("FXML/AskForm.fxml", "");
        AskFormController.message.setText("Вы действительно хотите покинуть игру?");
    }
}
