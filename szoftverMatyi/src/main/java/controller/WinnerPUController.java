package controller;

import game.LeaderBoard;
import game.Players;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * A játék végén felugró ablak kezelője.
 *
 * A WinnerPopUp.fxml elemeit vezérli.
 */
public class WinnerPUController {
    /**
     * A logoláshoz szükséges logger.
     */
    private static Logger logger = LoggerFactory.getLogger(WinnerPUController.class);
    /**
     * A győzteslistát grafikusan tartalmazó {@code VBox}.
     */
    @FXML
    VBox fxleaderboard;
    /**
     * Az ablak tetején megjelenő gratuláció, nicknévre szólóan.
     */
    @FXML
    Label congrats;

    /**
     * Az inicializáló függvény.
     *
     * Hozzáadja az új nevet a ranglistához.
     */
    @FXML
    void initialize() {
        fxleaderboard.getChildren().add(LeaderBoard.getLeaderBoardAsNode());
    }

    /**
     * Beállítja a {@code congrats Label} értékét.
     *
     * Győztes esetén a névre szóló gratulációra állítja a szöveget,
     * döntetlen esetén pedig az ahhoz illő szöveggel.
     * Döntetlen esemény, ha az összes színezhető mező elfogyott.
     * @param winner A győztes játékos nickneve, döntetlen esetén az értéke
     *               "TIE".
     */
    void printWinner(String winner) {
        if (winner.equals("TIE")) {
            congrats.setText("Do you know how to play??\n It's a tie.");
        } else {
            String text = "Congrats " + Players.getPlayer(winner) + ", you won!";
            congrats.setText(text);
        }
    }

    /**
     * Az új játék gombjának kezelője.
     *
     * A {@code Game.fxml} állományt tölti be.
     */
    @FXML
    public void playAgain() {
        Scene scene = fxleaderboard.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Game.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(root);
    }

    /**
     * A kilépést kezelő függvény.
     *
     * Meghívja a {@link MainMenuController} {@code exit()} függvényét.
     */
    @FXML
    public void exit() {
        new MainMenuController().exit();
    }
}
