package cpsc2150.extendedTicTacToe.test;
import org.junit.Test;
import static org.junit.Assert.*;
import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.IGameBoard;
import cpsc2150.extendedTicTacToe.models.GameBoard;
import cpsc2150.extendedTicTacToe.models.GameBoardMem;

public class Testing{
    enum Implentation {FAST,MEM};
    Implentation type = Implentation.FAST;
    int sizey = 60;
    int sizex = 10;
    int win = 5;

    @Test
    public void diagonalWinCheckNESW() {

        IGameBoard game = new GameBoard(0,0,0);
        if (type == Implentation.FAST)
            game = new GameBoard(sizex,sizey,win);
        if (type == Implentation.MEM)
            game = new GameBoardMem(sizex,sizey,win);

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
        IGameBoard game = new GameBoard(0,0,0);
        if (type == Implentation.FAST)
            game = new GameBoard(sizex,sizey,win);
        if (type == Implentation.MEM)
            game = new GameBoardMem(sizex,sizey,win);

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

        IGameBoard game = new GameBoard(0,0,0);
        if (type == Implentation.FAST)
            game = new GameBoard(sizex,sizey,win);
        if (type == Implentation.MEM)
            game = new GameBoardMem(sizex,sizey,win);
        BoardPosition pos = new BoardPosition(0,0);        

        

        
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    @Test
    public void isPlayerAtPosTest() {
        IGameBoard game = new GameBoard(0,0,0);
        if (type == Implentation.FAST)
            game = new GameBoard(sizex,sizey,win);
        if (type == Implentation.MEM)
            game = new GameBoardMem(sizex,sizey,win);

        BoardPosition pos = new BoardPosition(0,0);        

        pos = new BoardPosition(1,1);
        game.placeMarker(pos, 'X');
       
        System.out.println('\n'+game.toString());

        assertTrue(game.isPlayerAtPos(pos,'X'));
    }

}