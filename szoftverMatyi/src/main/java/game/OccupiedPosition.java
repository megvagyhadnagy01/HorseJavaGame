package game;

import javafx.scene.Node;

/**
 * A játékosok által elfogalt mezőket kezelő osztály.
 */
public class OccupiedPosition {
    /**
     * Az elfoglalandó terület koordinátáit tárolja x, y alapján
     * a táblán, {@code -1, -1}-re inicializálva.
     *
     */
    private int[] position = {-1, -1};
    private  int[] posx= {2, 1, -1, -2, -2, -1, 1, 2};
    private int[]posy= {1, 2, 2, 1, -1, -2, -2, -1};
    /**
     * Az elfoglalandó terület grafikus megjelenése.
     */
    private Node clickedNode;
    /**
     * Az elfoglalásesemények száma.
     */
    private static int eventCounter;

    /**
     * Megvizsgálja, hogy az adott táblán van-e még szabad hely.
     * <p>
     * A {@link Color}{@code .NONE} tulajdonságú mezők számítanak szabadnak,
     * ezek pont a tábla felét foglalják el.
     *
     * @param board A pillanatnyi táblareprezentáció
     * @return {@code true}, ha az elfoglalt helyek száma megegyezik a maximális szabad helyek számával,
     * {@code false} egyébként.
     */
    public boolean isTheBoardFull(Board board) {
        int boardSize = board.getBoard().size();
//        boolean isTheBoardFull = getEventCounter() >= (boardSize * boardSize) / 2;
        boolean isTheBoardFull = getEventCounter() >= (boardSize * boardSize) /2;
        if (isTheBoardFull) {
            setEventCounter(0);
        }
        return isTheBoardFull;
    }

    /**
     * {@code eventCounter} getter függvénye.
     *
     * @return eventCounter pillanatnyi értéke
     */
    public static int getEventCounter() {
        return eventCounter;
    }

    /**
     * {@code eventCounter} setter függvénye.
     *
     * @param eventCounter eventCounter új értéke
     */
    public static void setEventCounter(Integer eventCounter) {
        OccupiedPosition.eventCounter = eventCounter;
    }

    /**
     * {@code clickedNode} getter függvénye.
     *
     * @return clickedNode pillanatnyi értéke
     */
    public Node getClickedNode() {
        return clickedNode;
    }

    /**
     * {@code eventCounter} setter függvénye.
     *
     * @param clickedNode clickedNode új értéke
     */
    public void setClickedNode(Node clickedNode) {
        this.clickedNode = clickedNode;
    }

    /**
     * {@code position} getter függvénye.
     *
     * @return position pillanatnyi értéke
     */
    public int[] getPosition() {
        return position;
    }

    /**
     * {@code position} tömb elemeinek új értéke.
     * @param x koordináta {@code position[0]} űj értéke
     * @param y koordináta {@code position[1]} új értéke
     */
    public void setPosition(int x, int y) {
        this.position[0] = x;
        this.position[1] = y;
    }
}
