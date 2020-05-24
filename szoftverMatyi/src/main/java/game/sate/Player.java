package game.sate;

/**
 * A játékosok nicknevét kezelő osztály.
 */
public class Player {

    private String name;
    private int[] currentPosition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int[] currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Player(String name, int[] currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }
}
