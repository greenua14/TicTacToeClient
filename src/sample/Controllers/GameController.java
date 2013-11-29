package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable{

    public GridPane gameField;
    public static String figureStyle;
    public static int boxCount;
    public static int peopleCount;
    public static int PORT;
    public Button startGameButton;

    public static boolean makeStep(){
    return true;
    }

    private Pane createPane(int i, int j){
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

    private void createGameField(int n){
        for(int i=0;i<n;i++){
            gameField.getColumnConstraints().add(new ColumnConstraints(22));
            gameField.getRowConstraints().add(new RowConstraints(22));
            for(int j=0; j<n; j++){
                gameField.add(createPane(j,i),j,i);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxCount = CreateGameController.boxCount;
        peopleCount = CreateGameController.peopleCount;
        figureStyle = CreateGameController.figureStyle;
        PORT = CreateGameController.PORT;
        createGameField(boxCount);


    }

    public void startGame(ActionEvent actionEvent) {

    }
}
