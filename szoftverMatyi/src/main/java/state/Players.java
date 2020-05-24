package game.sate;

import java.util.ArrayList;

/**
 * A játékosok nicknevét kezelő osztály.
 */
public class Players {
    /**
     * A játékosok nickneveinek listája.
     */
    private static ArrayList<String> players = new ArrayList<>();

    /**
     * Adott játékos nevét adja vissza.
     *
     * @param player A játékos program által használt neve {@code (PLAYER1, PLAYER2)}
     * @return a kért játékos nickneve
     */
    public static String getPlayer(String player) {
        if (player.equals("PLAYER1")) {
            return players.get(0);
        } else if (player.equals("PLAYER2")) {
            return players.get(1);
        }
        return null;
    }

    /**
     * A játékosok nevét állítja be.
     *
     * @param player1 A {@code PLAYER1} neve
     * @param player2 A {@code PLAYER2} neve
     */
    public static void setPlayer(String player1, String player2) {
        players.add(0, player1);
        players.add(1, player2);
    }

}
