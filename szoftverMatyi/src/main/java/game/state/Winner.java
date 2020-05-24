package game.sate;

/**
 * A lehetséges győztes állapotokat tartalmazza.
 *
 * A lehetséges győztes állapotok:
 *
 * Enum osztály csak ezeket az állapotokat veheti fel mást nem !!
 */
public enum Winner {
    /**
     * Az egyes játékos.
     */
    PLAYER1,
    /**
     * A kettes játékos.
     */
    PLAYER2,
    /**
     * Döntetlen.
     */
    TIE,
    /**
     * Nincs nyertes (még).
     */
    NONE
}
