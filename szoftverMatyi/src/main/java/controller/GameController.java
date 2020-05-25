package controller;

import game.*;
import game.sate.Player;
import game.sate.Winner;
import javafx.collections.ObservableList;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

import static controller.GameUtils.currentPlayer;


/**
 * A fő játékablak controller osztálya.
 */
public class GameController  extends Field{
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
    A Tábla játékosainak modszere amelyben beálitjuk Player1 és Player2 töt.
     */
    public static final String PLAYER_1 = "PLAYER_1";
    public static final String PLAYER_2 = "PLAYER_2";
    public static final Map<String, Player> players = new HashMap<>();

    /**
     * A {@code Controller} inicializáló függvénye.
     */
    @FXML
    void initialize() {
        myBoard = new Board();
        drawBoard(myBoard, board);
        playerTurn.setText(players.get(PLAYER_1) + "'s turn");
        playerTurn.setText(players.get(PLAYER_2)+"'s turn");
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

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            Integer xNode = GridPane.getColumnIndex(node);
            Integer yNode = GridPane.getRowIndex(node);

            if(xNode != null && xNode == row && yNode != null && yNode == column) {
                result = node;
                break;
            }
        }

        return result;
    }

    /**
     * {@code FXML GridPane} eseménykezelő függvénye.
     * Ellenőrzi a lépés érvényességét ló lépésnek megfeleöen illetve
     * megjegyzi az elözö lépést Higlitel és feldobja a Játékos következö összes lépésének a lehetöségét
     * , és megvizsgálja, van-e győztes az új állapotban.
     * @param mouseEvent Az elkapott egéresemény (kattintás)
     */
    @FXML
    protected void gridClicked(MouseEvent mouseEvent)  {
        Node clickedNode = mouseEvent.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        Integer fieldID = Field.getFieldId();
        OccupiedPosition ofield = new OccupiedPosition();
//        OccupiedPosition ofieldx = new OccupiedPosition();
//        OccupiedPosition ofieldy = new OccupiedPosition();
//        final int WIDTH = 10;
//        final int HEIGHT = 10;


        if (!isThisAValidStep(colIndex, rowIndex)) {
           return;

        }
        /**
         *
         */
        Player putting = null;
        int otherX;
        int otherY;
        if(currentPlayer.equals(PLAYER_1)) {
            putting = players.get(PLAYER_1);
            otherX = players.get(PLAYER_2).getCurrentPosition()[0];
            otherY = players.get(PLAYER_2).getCurrentPosition()[1];
        } else {
            putting = players.get(PLAYER_2);
            otherX = players.get(PLAYER_1).getCurrentPosition()[0];
            otherY = players.get(PLAYER_1).getCurrentPosition()[1];
        }

        int myX = putting.getCurrentPosition()[0];
        int myY = putting.getCurrentPosition()[1];
        if(!validMove(myX, myY, colIndex, rowIndex)) {
            return;
        }

        if(myX != -1  && myY != -1) {
            Node toHighlight = getNodeByRowColumnIndex(myX, myY, board);
            ofield.setPosition(myX, myY);
            ofield.setClickedNode(toHighlight);
            GameUtils.changeColor(ofield, currentPlayer.equals(PLAYER_1) ? Color.PLAYER1 : Color.PLAYER2);

            List<Move> validMoves = generatePossibleMoves(myX, myY);
            for(Move move : validMoves) {
                toHighlight = getNodeByRowColumnIndex(move.x, move.y, board);
                ofield.setPosition(move.x, move.y);
                ofield.setClickedNode(toHighlight);
                GameUtils.changeColor(ofield, myBoard.getBoard().get(move.x).get(move.y).getColor());
            }
        } else {
            List<Move> validMoves = generatePossibleMoves(colIndex, rowIndex);
            for(Move move : validMoves) {
                Node toHighlight = getNodeByRowColumnIndex(move.x, move.y, board);
                ofield.setPosition(move.x, move.y);
                ofield.setClickedNode(toHighlight);
                GameUtils.changeColor(ofield, myBoard.getBoard().get(move.x).get(move.y).getColor());
            }
        }
        /**
         *
         */
        if (myBoard.getBoard().get(colIndex).get(rowIndex).getColor() == Color.NONE || myBoard.getBoard().get(colIndex).get(rowIndex).getColor() == Color.NONE2) {
            logger.info("x: " + colIndex + " y: " + rowIndex + " fieldID: " + fieldID);
            putting.setCurrentPosition(new int[]{colIndex,rowIndex});
            ofield.setPosition(colIndex, rowIndex);
            ofield.setClickedNode(clickedNode);
            GameUtils.writeTurn(playerTurn);
            String winner = GameUtils.changeColor(ofield, myBoard).toString();

            List<Move> validMoves = new ArrayList<>();
            if(otherX != -1  && otherY != -1) {
                Node toHighlight = getNodeByRowColumnIndex(otherX, otherY, board);
                ofield.setPosition(otherX, otherY);
                ofield.setClickedNode(toHighlight);
                GameUtils.changeColor(ofield, Color.HIGHLIGHT);

                validMoves = generatePossibleMoves(otherX, otherY);

                for(Move move : validMoves) {
                    toHighlight = getNodeByRowColumnIndex(move.x, move.y, board);
                    Color current = myBoard.getBoard().get(move.x).get(move.y).getColor();
                    if(current != Color.PLAYER1 && current != Color.PLAYER2) {
                        ofield.setPosition(move.x, move.y);
                        ofield.setClickedNode(toHighlight);
                        GameUtils.changeColor(ofield, Color.HIGHLIGHT_VALID);
                    }
                }
            }
            if(validMoves.isEmpty() && otherX != -1  && otherY != -1) {
              switchScene(Winner.TIE.toString());
            } else if (!winner.equals("NONE") && (!winner.equals("NONE2"))) {
                switchScene(winner);
            }
        }
    }

    private boolean validMove(int currentX, int currentY, int selectedX, int slectedY) {
        if(currentX == -1 && currentY == -1) {
            return true;
        }

        List<Move> validMoves = generatePossibleMoves(currentX, currentY);

        return validMoves.contains(new Move(selectedX, slectedY));
    }

    /**
     *
     */

    private static class Move {
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         *
         * @param other
         * @return
         */

        @Override
        public boolean equals(Object other) {
            if(this == other) {
                return true;
            }

            if(!(other instanceof Move)) {
                return false;
            }

            Move otherMove = (Move) other;
            return Objects.equals(this.x, otherMove.getX()) && Objects.equals(this.y, otherMove.getY());
        }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private List<Move> generatePossibleMoves(int x, int y) {
        List<Move> result = new ArrayList<>();

        final int width = Board.WIDTH;
        final int height = Board.HEIGHT;

        Move[] deltas = new Move[]{ new Move(-2, -1), new Move(-2, +1), new Move(+2, -1), new Move(+2, +1), new Move(-1, -2), new Move(-1, +2), new Move(+1, -2), new Move(+1, +2)};

        for (Move delta : deltas) {
            Move generated = new Move(x + delta.x, y + delta.y);
            if(generated.getX() > -1 && generated.getY() > -1 && generated.getX() < width && generated.getY() < height) {
                result.add(generated);
            }
        }

        return result;
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
        Color color = myBoard.getBoard().get(colIndex).get(rowIndex).getColor();
        return !(color.equals(Color.PLAYER1) || color.equals(Color.PLAYER2));
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
            UpdateLeaderBoard(players.get(winner).getName());
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