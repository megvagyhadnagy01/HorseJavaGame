package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A program futásakor megjelenő főmenűt kezelő osztály.
 */
public class MainMenuController {
    /**
     * A főmenő játékot indító gombja (a {@code Scene}
     * eléréséhez szükséges.
     */
    @FXML
    Button startbutton;

    /**
     * A játékot indító gomb kezelője.
     *
     * Betölti a {@code PlayerNames.fxml}-t.
     */
    @FXML
    protected void startGame() {
        Scene scene = startbutton.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/PlayerNames.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(root);
    }

    /**
     * A főmenű ranglistához vezető gombjának kezelője.
     *
     * Betölti a {@code Winners.fxml} állományt.
     */
    @FXML
    protected void showWinners() {
        Scene scene = startbutton.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Winners.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(root);
    }

    /**
     * A főmenű kilépést kezelő gombjának kezelője.
     *
     * Új {@code Stage}-t hoz létre, amin betölti a
     * {@code ExitDialog.fxml} állományt.
     */
    @FXML
    protected void exit() {
        Stage popup = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ExitDialog.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        popup.setTitle("Color War");
        popup.setScene(scene);
        popup.show();
    }
}
