package controller;


import game.*;
import game.sate.Players;
import game.sate.Winner;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

/**
 * A játék vezérlése során használt segédfüggvényeket tartalmazó osztály.
 */
public class GameUtils {

    /**
     * Az események logolására szolgáló Slf4j logger.
     */
    private static Logger logger = LoggerFactory.getLogger(GameUtils.class);

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

        if (ofield.isTheBoardFull(myBoard)) {
            return Winner.TIE;
        }

        int x = ofield.getPosition()[0];
        int y = ofield.getPosition()[1];
        Node clickedNode = ofield.getClickedNode();

        if (event % 2 == 0) {
            myBoard.setFieldColor(x, y, Color.PLAYER1);
            clickedNode.setStyle("-fx-background-color: "
                    + Color.PLAYER1.getColor()
                    + ";");
            logger.info("{} turn", Color.PLAYER1.getColor());

            if (isThereWinner(getColumn(myBoard, y))) {
                logger.info(Color.PLAYER1 + " won");
                return Winner.PLAYER1;
            }

        } else {
            myBoard.setFieldColor(x, y, Color.PLAYER2);
            clickedNode.setStyle("-fx-background-color: "
                    + Color.PLAYER2.getColor()
                    + ";");
            logger.info("{} turn", Color.PLAYER2.getColor());
            if (isThereWinner(myBoard.getBoard().get(x))) {
                logger.info(Color.PLAYER2 + " won");
                return Winner.PLAYER2;
            }
        }

        OccupiedPosition.setEventCounter(event + 1);

        return Winner.NONE;
    }

    /**
     * Megvizsgálja, hogy az adott {@code Field}-eket tartalmazó lista
     * elemei egyszínűek-e.
     *
     * @param fields A {@code Field} típusú lista
     * @return Ha mind egyszínű igaz, egyébként hamis.
     */
    public static boolean isThereWinner(ArrayList<Field> fields) {

        Color previous = Color.NONE;
//        Color previous2 =Color.NONE2;

        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                previous = fields.get(i).getColor();
                //continue;
            } else {
                if (previous != fields.get(i).getColor()) {
                    return false;
//                }
                }
            }
        }
//        Color previous2 =Color.NONE2;
//
//        for (int i = 0; i < fields.size(); i++) {
//            if (i == 0) {
//                previous2 = fields.get(i).getColor();
//                //continue;
//            } else {
//                if (previous2 != fields.get(i).getColor()) {
//                    return false;
////                }
//                }
//            }
//        }
            /////2jo megoldás
//        for (int i = 0; i < 5; i++) {  //fields.size()
//            if (i == 0) {
//                previous = fields.get(i).getColor() ;
//
//                //continue;
//            } else {
//                if (previous != fields.get(i).getColor()) {
//                    return false;
//                }
//              // for (int j = 0; j < 5; j++) {
//                     if (i == 1) {
//                        previous2 = fields.get(i).getColor();
//                        //continue;
//                    } else {
//                        if (previous2 != fields.get(i).getColor()) {
//                            return false;
//                        }
//
//                    }
//
//              // }
//
//
//            }
//        }
            /////
//        for (int i = 0; i < 5; i++) {  //fields.size()
//            if (i == 0) {
//                previous = fields.get(i).getColor();
//                //continue;
//            } else {
//                if (previous != fields.get(i).getColor()) {
//                    return false;
//                }
//                for (int j = 1; j < 5; j++) {
//                    if (j == 1) {
//                        previous2 = fields.get(j).getColor();
//                        //continue;
//                    } else {
//                        if (previous2 != fields.get(j).getColor()) {
//                            return false;
//                        }
//
//                    }
//
//                }
//
//
//            }
//        }


        return true;
    }



    /**
     * Az éppen következő játékos nevének megjelenését szabályozza.
     *
     * @param turn  a módosítandó {@code Label }
     */
    public static void writeTurn(Label turn) {
        int event = OccupiedPosition.getEventCounter();
        if (event % 2 == 1) {
            turn.setText(Players.getPlayer("PLAYER1") + "'s turn");
            turn.setId("p1Turn");
        } else {
            turn.setText(Players.getPlayer("PLAYER2") + "'s turn");
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
