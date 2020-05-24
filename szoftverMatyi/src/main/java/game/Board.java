package game;


import java.util.ArrayList;


/**
 * A tábla állapotának tárolásáért felelő osztály.
 */
public class Board {
    /**
     * A tábla {@code ArrayList<ArrayList<Field>>} reprezentációja.
     *
     * FONTOS! Az {@code ArrayList<ArrayList<>>} tárolás miatt a táblát 90 fokkal elforgatva
     * tároljuk, így a sor oszlopot sorként, a sort oszlopként kellene értelmezni.
     */
    private static ArrayList<ArrayList<Field>> board;
   // static final int TILE_SIZE = 72;
    static final int WIDTH = 10;
    static final int HEIGHT = 10;
//    public double getOldx() {
//        return oldx;
//    }
//
//    public double getOldy() {
//        return oldy;
//    }


    /**
     * A tábla konstruktora.
     *
     * Beállítja a tábla kezdeti állapotát (fehér és feketére).
     */
    public Board() {
        board = new ArrayList<>();

        for (int y = 0; y < HEIGHT; y++) {

            ArrayList<Field> row = new ArrayList<Field>();
            for (int x = 0; x < WIDTH; x++) {
                Field field = new Field();
                if ((x + y) % 2 == 0) {
                    field.setColor(Color.NONE);
                } else if ((x + y) % 2 !=0) {
                    field.setColor(Color.NONE2);
                }


                row.add(field);
            }

        board.add(row);
    }
        }



    /**
     * Beállítja az adott koordinátájú {@link Field} {@link Color} értékét.
     *
     * @param x  A {@link Field} x koordinátája
     * @param y  A {@link Field} y koordinátája
     * @param player Az x, y koordinátájú {@link Field} kívánt színe
     */
    public void setFieldColor(int x, int y, Color player) {
        board.get(x).get(y).setColor(player);
    }

    /**
     * A tábla reprezentációjának getter függvénye.
     * @return A tábla {@code ArrayList<ArrayList<Field>>} formában
     */
    public ArrayList<ArrayList<Field>> getBoard() {
        return board;
    }

    /**
     * A táblát olvasható {@code String} formátumra alakítja,
     * a konzolos megjelenése is mátrix.
     * @return tábla string reprezentációja
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (ArrayList<Field> fields : board) {
            string.append("\n");

            string.append(fields);
        }
        return string.toString();
    }
}
