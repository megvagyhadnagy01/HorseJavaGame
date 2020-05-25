package game.state;

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
    PLAYER_1,
    /**
     * A kettes játékos.
     */
    PLAYER_2,
    /**
     * Döntetlen.
     */
    TIE,
    /**
     * Nincs nyertes (még).
     */
    NONE
}
