package controller;

import game.*;
import game.sate.Winner;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GameUtilsTest {
    private Board board = new Board();

    private ArrayList<Field> fields = new ArrayList<>();

    private void fillWithSame(Color color) {
        fields = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Field field = new Field();
            field.setColor(color);
            fields.add(field);
        }

    }



    @Test
    public void getcolumnTest(){

        ArrayList<Field> column = GameUtils.getColumn(board, 0);
        assertNotNull(column.get(0));
        assertEquals(Color.NONE, column.get(0).getColor());
       // assertEquals(Color.NONE2, column.get(0).getColor());
        assertNotNull(column.get(1));
        assertEquals(Color.NONE2, column.get(1).getColor());
    }

    @Test
    public void isThereAwinnerWhenAllTheSameTest() {
        fillWithSame(Color.PLAYER2);
       // assertTrue(GameUtils.isThereWinner(fields));
    }

    @Test
    public void isThereAWinnerWhenNotAllTheSame() {
        fillWithSame(Color.PLAYER2);
        Field field = new Field();
        field.setColor(Color.PLAYER1);
        fields.add(field);
        //assertFalse(GameUtils.isThereWinner(fields));
    }

//    @Test
//    public void changeColorEmptyBoard() {
//        OccupiedPosition ofield = new OccupiedPosition();
//        ofield.setClickedNode(new StackPane());
//        ofield.setPosition(0, 0);
//
//        assertEquals(Winner.NONE, GameUtils.changeColor(ofield, board));
//        assertEquals(Color.PLAYER1, board.getBoard().get(0).get(0).getColor());
//
//        ofield.setClickedNode(new StackPane());
//        ofield.setPosition(0, 2);
//
//        assertEquals(Winner.NONE, GameUtils.changeColor(ofield, board));
//        assertEquals(Color.PLAYER2, board.getBoard().get(0).get(2).getColor());
//
//
//    }

    @Test
    public void changeColorFullBoard() {
        OccupiedPosition ofield = new OccupiedPosition();
        ofield.setClickedNode(new StackPane());
        OccupiedPosition.setEventCounter((board.getBoard().size() * board.getBoard().size()) / 2);
        ofield.setPosition(0, 0);
        assertEquals(Winner.NONE, GameUtils.changeColor(ofield, board));
    }


}
