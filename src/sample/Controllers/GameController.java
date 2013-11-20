package sample.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    public static Label PORT;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0;i<boxCount;i++){
            gameField.getColumnConstraints().add(new ColumnConstraints(22));
            gameField.getRowConstraints().add(new RowConstraints(22));
            for(int j=0; j<boxCount; j++){
                gameField.add(createPane(j,i),j,i);
            }
        }
        PORT.setLayoutX(gameField.getLayoutX()+22*boxCount+20);
    }
}
