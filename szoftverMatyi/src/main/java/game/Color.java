package game;

/**
 * A játékmezők színét tartalmazó enum.
 *
 * A konstansok értékeinek módosításával a mind a tárolt ({@code Board}), mint a megjelenített
 * ({@code Game.fxml}) színei megváltoznak. Az értékeket {@link javafx.scene.Node}{@code .setStyle()} használja
 * így az az által elfogadható értékek javasoltak.
 * FIGYELEM: A {@code Styles.css} által színezett részek felett nincs hatása:
 * {@code p1OnList}
 * {@code p2OnList}
 * {@code Player1Background}
 * {@code Player2Background}
 */
public enum Color {
    /**
     * Az egyes játékos által használt szín.
     */
    PLAYER1("BLUE"),
    /**
     * A kettes játékos által használt szín.
     */
    PLAYER2("RED"),
    /**
     * A szabad mezők színe1.
     */
    NONE("WHITE"),
    /**
     * A szabad mezők színe2
     */
   NONE2("BlACK");

    /**
     * A konstruktor működéséhez szükséges osztályváltozó.
     */
    private String color;

    /**
     * Az enum konstruktora.
     *
     * @param color A beállítani kívánt szín
     */
    Color(String color){
        this.color = color;
    }

    /**
     * A {@link Color} getter függvénye.
     * @return Az adott konstans értéke
     */
    public String getColor(){
        return this.color;
    }

}
