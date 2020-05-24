package game;

/**
 * A tábla egy mezőjét reprezentálja.
 *
 * Minden mezőnek egy tulajdonsága van, a pillanatnyi színe.
 */
public class Field {
    /**
     * {@link Color} típusú tulajdonsága a mezőnek.
     */
    private Color color;

    public static int getFieldId() {
        return fieldId;
    }

    private static int fieldId = 0;

    /**
     * Az alapértelmezett szín fehér ({@link Color}{@code .NONE}).
     */
    public Field() {
        this.color = Color.NONE;
        this.color =Color.NONE2;
        fieldId++;
    }


    /**
     * Mező színének setter függvénye.
     *
     * @param color A beállítandó {@link Color}
     */
    public void setColor(Color color) {
        this.color = color;
    }


    //public  int getID(return fieldId);

    /**
     * A mező színének getter függvénye.
     *
     * @return A mező pillanatnyi {@link Color} értéke
     */
    public Color getColor() {
        return color;
    }


    /**
     * A mező {@link String} megjelenése.
     *
     * @return A mező {@link Color} értéke
     */
    @Override

    public String toString() {
        return color.getColor();
    }

}






