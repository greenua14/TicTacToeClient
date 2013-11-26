package sample.Controllers;

import generalClasses.GameInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.InteractionWithServer;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.ArrayList;
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
        InteractionWithServer server = new InteractionWithServer();
        TableColumn fatherName = new TableColumn("Создал");
        TableColumn playersCountMax = new TableColumn("Макс. игроков");
        TableColumn playersCount = new TableColumn("Кол-во игроков");
        TableColumn fieldSize = new TableColumn("Размер поля");
        TableColumn button = new TableColumn();
        setDifParameters(fatherName, 200);
        setDifParameters(playersCount, 120);
        setDifParameters(playersCountMax, 120);
        setDifParameters(fieldSize, 100);
        setDifParameters(button, 60);

        createdGamesTable.setEditable(false);
        fatherName.setCellValueFactory(new PropertyValueFactory<GameInfo, String>("fatherLogin"));
        playersCount.setCellValueFactory(new PropertyValueFactory<GameInfo, String>("playersCountNow"));
        playersCountMax.setCellValueFactory(new PropertyValueFactory<GameInfo, String>("playersCountMax"));
        fieldSize.setCellValueFactory(new PropertyValueFactory<GameInfo, String>("fieldSize"));


        ObservableList<GameInfo> observableList = FXCollections.observableArrayList();
        for (Object obj : (ArrayList) server.checkCreatedGames()) {
            observableList.add((GameInfo) obj);
        }
        createdGamesTable.getItems().setAll(observableList);
        createdGamesTable.getColumns().addAll(fatherName, playersCount, playersCountMax, fieldSize);

        /*final ObservableList<Person> data = FXCollections.observableArrayList(new Person("asd","qew"));
        TableColumn number = new TableColumn("№");
        TableColumn fatherName = new TableColumn("Создал");
        number.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

        fatherName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

        createdGamesTable.setItems(data);
        createdGamesTable.getColumns().addAll(number, fatherName);*/
    }

    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;

        private Person(String fName, String lName) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);

        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

    }

}

