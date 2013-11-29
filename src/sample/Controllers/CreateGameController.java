package sample.Controllers;

import generalClasses.GameInfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.InteractionWithServer;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateGameController implements Initializable {
    public Hyperlink backLink;
    public Button createGameButton;
    public ComboBox<String> playersCount;
    public ComboBox<String> fieldSize;
    public ComboBox<Label> pictures;
    private Stage stage = null;
    public static int PORT;
    public static int peopleCount;
    public static int boxCount;
    public static String figureStyle;

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
        Label figStyle = pictures.getValue();

        if (fs == null || pc == null || figStyle == null) {
            new LoadSomeForm().load("FXML/errorForm.fxml", "Error");
            ErrorFormController.textErrorID.setText("Выберите количество игроков, размер поля и фигуру");
        } else {
            InteractionWithServer server = new InteractionWithServer();
            peopleCount = Integer.parseInt(pc.toString());
            boxCount = Integer.parseInt(fs.toString().substring(0, 2));
            figureStyle = figStyle.getId();
            String title = ((Stage) createGameButton.getScene().getWindow()).getTitle();
            try {
                GameInfo gameInfo = new GameInfo(title, peopleCount, boxCount, figureStyle);
                gameInfo.addToPlayersList(title, figureStyle);
                PORT = server.createGame(gameInfo);
                new LoadSomeForm().load("FXML/Game.fxml", title);
                closeThis();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Label generatePictures(String path){
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(path));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);
        Label label = new Label();
        label.setGraphic(stackPane);
        label.setId(path.substring(path.length()-7,path.length()));
        return label;
    }

    private void addPictures(){
        pictures.getItems().addAll(generatePictures("sample/FXML/css/images/hn6.png"),
                generatePictures("sample/FXML/css/images/hn2.png"),
                generatePictures("sample/FXML/css/images/hn3.png"),
                generatePictures("sample/FXML/css/images/hn4.png"),
                generatePictures("sample/FXML/css/images/hn5.png"));
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

        addPictures();

        pictures.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pictures.getItems().retainAll();
                addPictures();
            }
        });


    }
}
