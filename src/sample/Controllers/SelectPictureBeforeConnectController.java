package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.InteractionWithServer;
import sample.LoadSomeForm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class SelectPictureBeforeConnectController implements Initializable {
    public ComboBox selectPicture;
    private ArrayList<String> picturesList = new ArrayList<>();

    public void connectToGame(ActionEvent actionEvent) {
        InteractionWithServer server = new InteractionWithServer((Integer) CreateOrConnectToGameController.infoForConnect.get("PORT"));
        System.out.println(CreateOrConnectToGameController.infoForConnect.get("PORT"));
        try {
            server.connectToCreatedGame(CreateOrConnectToGameController.infoForConnect.get("login").toString(),
                    ((Label)selectPicture.getValue()).getId());
            new LoadSomeForm().load("FXML/Game.fxml", CreateOrConnectToGameController.stage.getTitle());
            ((Stage) selectPicture.getScene().getWindow()).close();
            CreateOrConnectToGameController.stage.close();
        } catch (IOException e) {
            new LoadSomeForm().showErrorMessage("Сервер не отвечает");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkFreePictures(CreateOrConnectToGameController.infoForConnect.get("picture").toString());
        addPictures();

        selectPicture.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                selectPicture.getItems().retainAll();
                addPictures();
            }
        });
    }

    private void addPictures() {
        for (String s : picturesList) {
            selectPicture.getItems().add(generatePictures(s));
        }
    }

    private Label generatePictures(String path) {
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(path));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);
        Label label = new Label();
        label.setGraphic(stackPane);
        label.setId(path.substring(path.length() - 7, path.length()));
        return label;
    }

    private void checkFreePictures(String pic) {
        picturesList.add("sample/FXML/css/images/hn6.png");
        picturesList.add("sample/FXML/css/images/hn2.png");
        picturesList.add("sample/FXML/css/images/hn3.png");
        picturesList.add("sample/FXML/css/images/hn4.png");
        picturesList.add("sample/FXML/css/images/hn5.png");
        ArrayList<String> usedPicture = new ArrayList<>();
        while (pic.length() != 0) {
            usedPicture.add(pic.substring(0, 7));
            pic = pic.substring(7, pic.length());
        }
        Iterator<String> iterator = picturesList.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            for (String usedPic : usedPicture) {
                if (next.contains(usedPic)) {
                    iterator.remove();
                }
            }

        }
    }
}
