package cpsc2150.extendedTicTacToe.test;
import org.junit.Test;
import static org.junit.Assert.*;
import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.GameBoard;

public class Testing{
    int sizey = 60;
    int sizex = 10;
    int win = 5;

    @Test
    public void diagonalWinCheckNESW() {
        GameBoard game = new GameBoard(sizex,sizey,win);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition((sizey-10)-i,i);
            game.placeMarker(pos, 'X');
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    @Test
    public void diagonalWinCheckSENW() {
        GameBoard game = new GameBoard(sizex,sizey,win);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition(i, i);
            game.placeMarker(pos, 'X');
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    @Test
    public void drawCheck() {

        int dsizey = 5;
        int dsizex = 5;
        int dwin = 5;

        GameBoard game = new GameBoard(dsizex,dsizey,dwin);

        BoardPosition pos = new BoardPosition(0,0);        

        

        
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

}