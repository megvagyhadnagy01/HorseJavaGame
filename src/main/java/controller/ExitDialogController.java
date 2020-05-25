package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * A kilépést megerősítő felugró ablak controllere.
 */
public class ExitDialogController {
    /**
     * A felugró ablak Cancel gombja, a {@code Scene} kezeléséhez
     * szükséges.
     */
    @FXML
    Button cancel;

    /**
     * A megerősítő gomb kezelő függvénye.
     *
     * Aktiváláskor leállítja az egész programot {@code exit(0)}
     * státusszal.
     */
    @FXML
    protected void EnsureExit() {
        System.exit(0);
    }

    /**
     * A visszavonó gomb kezelője.
     *
     * Aktiváláskor bezárja a felugró ablakot.
     */
    @FXML
    protected void CancelExit() {
        Stage currentStage = (Stage) cancel.getScene().getWindow();
        currentStage.close();
    }
}
