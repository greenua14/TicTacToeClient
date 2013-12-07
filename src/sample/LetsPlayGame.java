package sample;

import generalClasses.GameInfo;
import sample.Controllers.GameController;

import java.util.HashMap;

public class LetsPlayGame implements Runnable{
    private GameInfo gameInfo;

    public LetsPlayGame(GameInfo gameInfo){
        this.gameInfo = gameInfo;
    }

    @Override
    public void run() {
        System.out.println("poshlo");
        HashMap<String, String> playersList = new InteractionWithServer(gameInfo.getPORT()).waitPlayers().getPlayersList();

        System.out.println("prishlo");


        for (String s : playersList.keySet()) {
            System.out.println(s +"="+playersList.get(s));
        }

        GameController.rebuildPlayersTable(playersList);


        //System.out.println(info1.getLogin());
    }
}
