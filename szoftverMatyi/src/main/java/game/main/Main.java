package game.main;

/**
 * A program ál Main osztálya.
 *
 * Ez az osztály indul indításkor, és hívja a {@link GameMaster} osztály
 * {@code main(String[])} függvényét.
 * A megoldásról több:
 * https://github.com/javafxports/openjdk-jfx/issues/236
 */
public class Main {
    /**
     * Main függvény.
     * @param args parancssor argumentumok
     */
    public static void main(String[] args){
        GameMaster.main(args);
    }
}
