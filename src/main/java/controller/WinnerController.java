package controller;

import game.LeaderBoard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * A főmenűből elérhető ranglistát kezelő controller.
 */
public class WinnerController {
    /**
     * A logoláshoz szükséges logger.
     */
    private static Logger logger = LoggerFactory.getLogger(WinnerController.class);
    /**
     * A ranglistát grafikusan tartalmazó {@code VBox}.
     */
    @FXML
    VBox leaderboard;

    /**
     * Az osztály inicializáló függvénye.
     *
     * Betölti a {@code leaderboard.xml} állományt.
     */
    @FXML
    public void initialize() {
        JAXBUtil.readFromXML(logger);
        leaderboard.getChildren().add(LeaderBoard.getLeaderBoardAsNode());
    }

    /**
     * A visszalépés gomb kezelő függvénye.
     *
     * Újra betölti a {@code MainMenu.fxml} állományt,
     */
    @FXML
    protected void backToMain() {
        Scene scene = leaderboard.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(root);
    }

}
