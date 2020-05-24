package controller;

import game.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * A fő játékablak controller osztálya.
 */
public class GameController {
    /**
     * A logoláshoz szükséges logger.
     */
    private static Logger logger = LoggerFactory.getLogger(GameController.class);
    /**
     * A játékablakban elhelyezett {@code GridPane}, a grafikus megjelenése a
     * táblának.
     */
    @FXML
    GridPane board;
    /**
     * A játékablak grafikájának gyökéreleme, {@code BorderPane}.
     */
    @FXML
    BorderPane root;
    /**
     * Az 1-es játékos nicknevét megjelenítő {@code Label}.
     */
    @FXML
    Label p1Name;
    /**
     * A 2-es játékos nicknevét megjelenítő {@code Label}.
     */
    @FXML
    Label p2Name;
    /**
     * Az épp soron következő játékos nicknevét megjelenítő {@code Label}.
     */
    @FXML
    Label playerTurn;
    /**
     * A játéktábla állását tároló {@link Board} objektum.
     */
    private Board myBoard;

    /**
     * A {@code Controller} inicializáló függvénye.
     */
    @FXML
    void initialize() {
        myBoard = new Board();
        drawBoard(myBoard, board);
        p1Name.setText(Players.getPlayer("PLAYER1") + "\n (Horizontal)");
        p2Name.setText(Players.getPlayer("PLAYER2") + "\n (Vertical)");
        playerTurn.setText(Players.getPlayer("PLAYER1") + "'s turn");
    }

    /**
     * {@code FXML Button} vissza a főmenűbe eseménykezelő.
     *
     * Betölti a {@code MainMenu.fxml} állományt.
     */
    @FXML
    protected void backToMain() {

        Scene scene = board.getScene();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setRoot(root);
    }

    /**
     * {@code FXML Button} kilépés eseménykezelő.
     *
     * Meghívja a MainMenuController azonos nevű függvényét.
     */
    @FXML
    protected void exit() {
        new MainMenuController().exit();
    }

    /**
     * {@code FXML GridPane} eseménykezelő függvénye.
     * Ellenőrzi a lépés érvényességét, és megvizsgálja, van-e győztes az új állapotban.
     * @param mouseEvent Az elkapott egéresemény (kattintás)
     */
    @FXML
    protected void gridClicked(MouseEvent mouseEvent) {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        OccupiedPosition ofield = new OccupiedPosition();

        if (!isThisAValidStep(colIndex, rowIndex)) {
            return;
        }

        if (myBoard.getBoard().get(colIndex).get(rowIndex).getColor() == Color.NONE || myBoard.getBoard().get(colIndex).get(rowIndex).getColor() == Color.NONE2) {
            logger.info("x: " + colIndex + " y: " + rowIndex);
            ofield.setPosition(colIndex, rowIndex);
            ofield.setClickedNode(clickedNode);
            GameUtils.writeTurn(playerTurn);
            String winner = GameUtils.changeColor(ofield, myBoard).toString();
            if (!winner.equals("NONE") && (!winner.equals("NONE2"))) {
                switchScene(winner);
            }
        }
//        if (myBoard.getBoard().get(colIndex).get(rowIndex).getColor() == Color.NONE2) {
//            logger.info("x: " + colIndex + " y: " + rowIndex);
//            ofield.setPosition(colIndex, rowIndex);
//            ofield.setClickedNode(clickedNode);
//            GameUtils.writeTurn(playerTurn);
//            String winner = GameUtils.changeColor(ofield, myBoard).toString();
//            if (!winner.equals("NONE2")) {
//                switchScene(winner);
//            }
//        }

    }

    /**
     * Ellenőrzi, hogy az elkapott kattintás valóban a {@code GridPane}
     * területére esik-e.
     *
     * @param colIndex A kattintás {@code GridPane}-beli oszlopindexe
     * @param rowIndex A kattintás {@code GridPane}-beli sorindexe
     * @return {@code true} ha érvényes koordináta, {@code false} egyébként
     */
    private boolean isThisAValidStep(Integer colIndex, Integer rowIndex) {
        try {
            myBoard.getBoard().get(colIndex).get(rowIndex).getColor();

        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Átváltja az akív {@code Scene}-t a győztesnek gratuláló {@code Scene}-re.
     * Betölti a {@code WinnerPopUp.fxml} állományt.
     *
     * @param winner A győztes játékos neve
     */
    @FXML
    private void switchScene(String winner) {

        Scene scene = board.getScene();
        if(!winner.equals("TIE")){
            UpdateLeaderBoard(Players.getPlayer(winner));
        } else {
            JAXBUtil.readFromXML(logger);
        }
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WinnerPopUp.fxml"));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WinnerPUController controller = loader.getController();
        controller.printWinner(winner);
        scene.setRoot(root);
        logger.info("{}", scene.getRoot());

    }

    /**
     * Frissíti a ranglista értékeit, beolvassa, hozzáfűzi az új értéket
     * majd újra kiírja.
     *
     * @param winner a hozzáfűzendő nickname
     */
    private void UpdateLeaderBoard(String winner) {
        LeaderBoard leaderBoard = JAXBUtil.readFromXML(logger);
        LeaderBoard.addName(winner);
        JAXBUtil.writeToXML(leaderBoard, logger);
    }

    /**
     * A tábla grafikus reprezentációját inicializálja.
     *
     * @param myBoard A kirajzolandó tábla mátrixa
     * @param boardUI inicializálandó {@code GridPane}
     */
    private void drawBoard(Board myBoard, GridPane boardUI) {
        for (int i = 0; i < myBoard.getBoard().size(); i++) {
            for (int j = 0; j < myBoard.getBoard().get(i).size(); j++) {
                StackPane square = new StackPane();
                square.setMinSize(50, 50);
                square.setStyle("-fx-background-color: "
                        + myBoard.getBoard().get(i).get(j)
                        + ";");
                boardUI.add(square, i, j);
            }
        }
        logger.info("Board initial state done");
    }
}