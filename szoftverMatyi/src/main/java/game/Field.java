package game;

/**
 * A tábla egy mezőjét reprezentálja.
 *
 * Minden mezőnek egy tulajdonsága van, a pillanatnyi színe.
 */
public class Field {
    /**
     * {@link Color} típusú tulajdonsága a mezőnek.
     * {@link Board} tábla fieldjeinek id-ja.
     */
    private Color color;

//    public static int getFieldId() {
//        return fieldId;
//    }

    private static int fieldId = 0;

    /**
     * Az alapértelmezett szín fehér ({@link Color}{@code .NONE és NONE2}).
     */
    public Field() {
        this.color = Color.NONE;
        this.color =Color.NONE2;
        fieldId++;
    }


    /**
     * Mező színének setter függvénye.
     *
     *
     * @param color A beállítandó {@link Color}
     */
    public void setColor(Color color) {
        this.color = color;
    }


    //public  int getID(return fieldId);

    /**
     * A mező színének getter függvénye.
     *A filedIdjának getter fügvénye.
     * @return  A mező pillanatnyi {@link Color} értéke
     * @return A mező pillanatnyi {@link Board} értéke
     */
    public Color getColor() {
        return color;
    }

    public static int getFieldId() {
        return fieldId;
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






