package sample.Controllers;

import generalClasses.GameInfo;
import generalClasses.GameInfoProperties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.InteractionWithServer;
import sample.LoadSomeForm;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CreateOrConnectToGameController implements Initializable {
    public Button createGameButton;
    public TableView createdGamesTable;
    public static Stage stage = null;
    public static HashMap infoForConnect;

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
        TableColumn port = new TableColumn("port");
        TableColumn pictures = new TableColumn("pictures");
        setDifParameters(fatherName, 210);
        setDifParameters(playersCount, 130);
        setDifParameters(playersCountMax, 130);
        setDifParameters(fieldSize, 126);
        final ObservableList<GameInfoProperties> observableList = FXCollections.observableArrayList();

        class StringTableCell extends TableCell<GameInfoProperties, String> {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : getString());
                setGraphic(null);
            }

            private String getString() {
                return getItem() == null ? "" : getItem().toString();
            }
        }

        class MyEventHandler implements EventHandler<MouseEvent> {
            @Override
            public void handle(MouseEvent t) {
                if (t.getButton().equals(MouseButton.PRIMARY)) {
                    if (t.getClickCount() == 2) {
                        TableCell tableCell = (TableCell) t.getSource();
                        if (tableCell.getItem() != null) {
                            int index = tableCell.getIndex();
                            if (observableList.get(index).getPlayersCountMax() == observableList.get(index).getPlayersCountNow()) {
                                new LoadSomeForm().load("FXML/ErrorForm.fxml", "error");
                                ErrorFormController.textErrorID.setText("Нету свободных мест, выберите другую игру");
                            } else {
                                stage = ((Stage) createGameButton.getScene().getWindow());
                                infoForConnect = new HashMap();
                                infoForConnect.put("picture", observableList.get(index).getPictures());
                                infoForConnect.put("PORT", observableList.get(index).getPORT());
                                infoForConnect.put("login", ((Stage) createGameButton.getScene().getWindow()).getTitle());
                                new LoadSomeForm().load("FXML/SelectPictureBeforeConnect.fxml", "");
                            }
                        }
                    }
                }
            }
        }

        class IntegerTableCell extends TableCell<GameInfoProperties, Integer> {
            @Override
            public void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : getString());
                setGraphic(null);
            }

            private String getString() {
                return getItem() == null ? "" : getItem().toString();
            }
        }

        Callback<TableColumn, TableCell> stringCellFactory =
                new Callback<TableColumn, TableCell>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        StringTableCell cell = new StringTableCell();
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
                        return cell;
                    }
                };
        Callback<TableColumn, TableCell> integerCellFactory =
                new Callback<TableColumn, TableCell>() {
                    @Override
                    public TableCell call(TableColumn p) {
                        IntegerTableCell cell = new IntegerTableCell();
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());
                        return cell;
                    }
                };

        createdGamesTable.setEditable(false);
        fatherName.setCellValueFactory(new PropertyValueFactory<GameInfoProperties, String>("fatherLogin"));
        playersCount.setCellValueFactory(new PropertyValueFactory<GameInfoProperties, Integer>("playersCountNow"));
        playersCountMax.setCellValueFactory(new PropertyValueFactory<GameInfoProperties, Integer>("playersCountMax"));
        fieldSize.setCellValueFactory(new PropertyValueFactory<GameInfoProperties, Integer>("fieldSize"));
        port.setCellValueFactory(new PropertyValueFactory<GameInfoProperties, Integer>("PORT"));
        pictures.setCellValueFactory(new PropertyValueFactory<GameInfoProperties, String>("pictures"));
        fatherName.setCellFactory(stringCellFactory);
        playersCount.setCellFactory(integerCellFactory);
        playersCountMax.setCellFactory(integerCellFactory);
        fieldSize.setCellFactory(integerCellFactory);
        port.setCellFactory(integerCellFactory);
        pictures.setCellFactory(stringCellFactory);
        port.setVisible(false);
        pictures.setVisible(false);

        for (Object obj : (ArrayList) server.checkCreatedGames()) {
            observableList.add(new GameInfoProperties((GameInfo) obj));
        }

        createdGamesTable.getColumns().addAll(fatherName, playersCount, playersCountMax, fieldSize, port, pictures);
        createdGamesTable.getItems().setAll(observableList);
    }
}


