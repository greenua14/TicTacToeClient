package sample;

import generalClasses.GameInfo;
import generalClasses.Step;
import javafx.application.Platform;
import javafx.stage.Stage;
import sample.Controllers.CreateOrConnectToGameController;
import sample.Controllers.ErrorFormController;
import sample.Controllers.GameController;

import java.io.IOException;
import java.util.HashMap;

public class LetsPlayGame implements Runnable {
    private HashMap<String, String> playersList;
    private static boolean flag = true;
    private boolean isRead = true;
    private static Client client;

    public LetsPlayGame(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (flag) {
                Object postFromServer = client.getInputStream().readObject();

                if (postFromServer.getClass().equals(GameInfo.class)) {      //refresh players list
                    final GameInfo gameInfo = (GameInfo) postFromServer;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            GameController.rebuildPlayersTable(gameInfo.getPlayersList());
                        }
                    });
                }

                if (postFromServer.getClass().equals(Step.class)) {
                    final Step step = (Step) postFromServer;
                    System.out.println("step prishol");
                    if (step.isAllLeave()) {            //if all players leaved from game
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Stage stage = (Stage) GameController.gameField.getScene().getWindow();
                                new LoadSomeForm().load("FXML/CreateOrConnectToGame.fxml", stage.getTitle());
                                CreateOrConnectToGameController.mainForm.setDisable(true);
                                stage.close();
                                new LoadSomeForm().load("FXML/errorForm.fxml", "oops", GameController.eventHandlerForCloseStage);
                                ErrorFormController.textErrorID.setText("Все игроки покинули игру...");

                            }
                        });
                        stopWorking();

                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //GameController.gameField.setDisable(false);

                                System.out.println("stepLogin = " + step.getLoginNext());
                                GameController.startTimer(step.getLoginNext());
                            }
                        });
                    }
                }
            }


        } catch (Exception e) {
            e.getCause();
            System.out.println("-------------------------");
            e.getMessage();
            System.out.println("-------------------------");

            e.printStackTrace();
        }
    }

    public void stopWorking() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    flag = false;
                    client.getInputStream().close();
                    if (!Thread.currentThread().isInterrupted()){
                        Thread.currentThread().interrupt();
                    }else{
                        System.out.println("srabotal stop");
                        Thread.currentThread().stop();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }

            }
        });

    }
}
