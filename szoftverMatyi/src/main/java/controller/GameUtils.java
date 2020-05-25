package controller;


import game.*;
import game.sate.Winner;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

import static controller.GameController.*;

/**
 * A játék vezérlése során használt segédfüggvényeket tartalmazó osztály.
 */
public class GameUtils {

    /**
     * Az események logolására szolgáló Slf4j logger.
     */
    private static Logger logger = LoggerFactory.getLogger(GameUtils.class);

    public static String currentPlayer = PLAYER_1;

    /**
     * A táblán a játékosok által "elfoglalt" mezőket színezi át a megfelelő színűre.
     *
     * Az átszinezés mind az adatszerkezetben, mind a grafikán történik.
     * @param ofield  A bekattinott {@code Node} elem
     * @param myBoard A jelenlegi játéktáblaállás
     * @return A nyertes értékét ({@code PLAYER1, PLAYER2, TIE, NONE})
     */
    public static Winner changeColor(OccupiedPosition ofield, Board myBoard) {

        int event = OccupiedPosition.getEventCounter();
        int x = ofield.getPosition()[0];
        int y = ofield.getPosition()[1];
        Node clickedNode = ofield.getClickedNode();

        if (event % 2 == 0) {
            myBoard.setFieldColor(x, y, Color.PLAYER1);
            clickedNode.setStyle("-fx-background-color: "
                    + Color.PLAYER1.getColor()
                    + ";");
            logger.info("{} turn", Color.PLAYER1.getColor());

            if (isThereWinner(myBoard.getBoard())) {
                logger.info(Color.PLAYER1 + " won");
                return Winner.PLAYER_1;
            }
        } else {
            myBoard.setFieldColor(x, y, Color.PLAYER2);
            clickedNode.setStyle("-fx-background-color: "
                    + Color.PLAYER2.getColor()
                    + ";");
            logger.info("{} turn", Color.PLAYER2.getColor());
            if (isThereWinner(myBoard.getBoard())) {
                logger.info(Color.PLAYER2 + " won");
                return Winner.PLAYER_2;
            }
        }

        OccupiedPosition.setEventCounter(event + 1);

        return Winner.NONE;
    }

    public static void changeColor(OccupiedPosition ofield, Color color) {
        int x = ofield.getPosition()[0];
        int y = ofield.getPosition()[1];
        Node clickedNode = ofield.getClickedNode();
        clickedNode.setStyle("-fx-background-color: "
                + color.getColor()
                + ";");
    }

    /**
     * Megvizsgálja, hogy az adott {@code Field}-eket tartalmazó lista
     * elemei egyszínűek-e soronként a Blue játékosnak
     * illetve oszloponként a Red játékosnak.
     *
     * @return Ha mind egyszínű igaz, egyébként hamis.
     */
    public static boolean isThereWinner(ArrayList<ArrayList<Field>> board) {

        for (int i = 0; i < Board.WIDTH; i++) {

            for(int j = 0; j < Board.HEIGHT; j++) {
                Field current = board.get(i).get(j);
                int colorCounter = 0;
                if(current.getColor().equals(Color.PLAYER1)) {
                    for(int k = i; k < Board.WIDTH; k++) {
                        if(board.get(k).get(j).getColor().equals(Color.PLAYER1)) {
                            colorCounter++;
                            if(colorCounter > 4) {
                                return true;
                            }
                        } else {
                            colorCounter = 0;
                        }
                    }
                } else if(current.getColor().equals(Color.PLAYER2)) {
                    for(int k = j; k < Board.HEIGHT; k++) {
                        if(board.get(i).get(k).getColor().equals(Color.PLAYER2)) {
                            colorCounter++;
                            if(colorCounter > 4) {
                                return true;
                            }
                        } else {
                            colorCounter = 0;
                        }
                    }
                }
            }
        }
        return false;
    }



    /**
     * Az éppen következő játékos nevének megjelenését szabályozza.
     *
     * @param turn  a módosítandó {@code Label }
     */
    public static void writeTurn(Label turn) {
        int event = OccupiedPosition.getEventCounter();
        if (event % 2 == 1) {
            currentPlayer = PLAYER_1;
            turn.setText(players.get(PLAYER_1).getName() + "'s turn");
            turn.setId("p1Turn");
        } else {
            currentPlayer = PLAYER_2;
            turn.setText(players.get(PLAYER_2).getName() + "'s turn");
            turn.setId("p2Turn");
        }
    }

    /**
     * Egy tábla {@code column}. oszlopát adja vissza.
     * Azért van rá szükség, mert a {@code Board} táblát leképező adatszerketete
     * egy {@code ArrayList<ArrayList<Field>>}, aminek csak a sorát kérhetjük le
     * beépített függvénnyel.
     *
     * @param myBoard {@code Board} típusú objektum, a bemeneti tábla
     * @param column  a tábla adott oszlopindexe
     * @return {@code ArrayList<Field>} érték, a tábla egy oszlopa
     */
    public static ArrayList<Field> getColumn(Board myBoard, int column) {
        ArrayList<Field> toReturn = new ArrayList<>();
        for (int i = 0; i < myBoard.getBoard().size(); i++) {
            toReturn.add(myBoard.getBoard().get(i).get(column));
        }
        return toReturn;
    }
}
