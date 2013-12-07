package sample.Controllers;

import generalClasses.GameInfo;
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
import sample.LetsPlayGame;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public GridPane gameField;
    public Button startGameButton;
    public static Pane playersTable;
    public static Pane lostGameLink;
    public Label playersCount;
    public static Stage stage;
    public static GameInfo info = null;


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

    private static void addPlayersToTable(Map<String, String> list) {
        int n = 0;
        for (Map.Entry<String, String> entry : list.entrySet()) {
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
            pane.setLayoutY(n);
            n += 35;
            playersTable.getChildren().add(pane);
        }
        lostGameLink.setLayoutY(n + 60);
    }


    public static void rebuildPlayersTable(Map<String, String> list) {
        playersTable.getChildren().retainAll();
        addPlayersToTable(list);
    }

    private void addPlayerToTable(String login, String picture) {
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
        pane.setLayoutY(0);
        playersTable.getChildren().add(pane);
        lostGameLink.setLayoutY(140);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (CreateGameController.gameInfo != null) {
            info = CreateGameController.gameInfo;
            createPlayersTable(50);
            addPlayerToTable(info.getFatherLogin(), info.getPictures());
            playersCount.setText(info.getPlayersCountNow() + " / " + info.getPlayersCountMax());

        } else {
            System.out.println("1");
            info = CreateOrConnectToGameController.infoForConnect;
            createPlayersTable(50);
            addPlayersToTable(SelectPictureBeforeConnectController.playersList);
            playersCount.setText((info.getPlayersCountNow()+1) + " / " + info.getPlayersCountMax());
            System.out.println("2");
        }
        createGameField(info.getFieldSize());
        gameField.setDisable(true);
        System.out.println("3");
        Thread thread = new Thread(new LetsPlayGame(info));
        thread.start();
        System.out.println("4");

    }

    public void lostGame(ActionEvent actionEvent) {
        stage = (Stage) gameField.getScene().getWindow();
        new LoadSomeForm().load("FXML/AskForm.fxml", "");
        AskFormController.message.setText("Вы действительно хотите покинуть игру?");
    }

}
