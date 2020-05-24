package controller;

import game.Players;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * A játék főmenűből való indításakor megjelenő
 * nicknév bekérés kezelő osztálya.
 */
public class PlayerNController {
    /**
     * A nicknevek rögzítését végző gomb, (a {@code Scene}
     * eléréséhez szükséges.
     */
    @FXML
    Button submitNames;
    /**
     * Az 1-es játékos nicknevét váró {@code TextField}.
     */
    @FXML
    TextField player1;
    /**
     * A 2-es játékos nicknevét váró {@code TextField}.
     */
    @FXML
    TextField player2;
    /**
     * Ha nem adunk nicknevet valamelyik {@code TextField} mezőnek,
     * abban az esetben aktiválódik ez a {@code Label} "Type both names" szöveggel.
     *
     * A {@code Styles.css} állományból #warning id-vel hivatkozható.
     */
    @FXML
    Label warning;

    /**
     * A nevek rögzítését kezelő függvény.
     *
     * Üres {@code Textfield}-ek esetén aktiválja a {@code warning Label}-t
     * "Type both name!" felirattal (#warning .css-ből), egyébként
     * betölti a {@code Game.fxml} állományt.
     */
    @FXML
    protected void submitNames() {
        if (player1.getText().equals("") || player2.getText().equals("")) {
            warning.setText("Type both name!");
        } else {
            Players.setPlayer(player1.getText(), player2.getText());
            Scene scene = warning.getScene();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/Game.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene.setRoot(root);
        }
    }

    /**
     * A menübe való visszalépés gombját kezelő függvény.
     *
     * Újra betölti a {@code MainMenu.fxml}-t.
     */
    @FXML
    protected void backToMain() {
        Scene scene = submitNames.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(root);
    }
}
