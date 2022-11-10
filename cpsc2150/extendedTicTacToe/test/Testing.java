package cpsc2150.extendedTicTacToe.test;
import org.junit.Test;
import static org.junit.Assert.*;
import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.IGameBoard;
import cpsc2150.extendedTicTacToe.models.GameBoard;
import cpsc2150.extendedTicTacToe.models.GameBoardMem;

import java.beans.Transient;
import java.util.Random;

public class Testing{

    int sizey = 60;
    int sizex = 13;
    int win = 5;

    private IGameBoard gameB() {	
        return new GameBoard(sizex, sizey, win);
    }

    enum Implentation {FAST,MEM};
    Implentation type = Implentation.FAST;
   


    // Constructor


//checkSpace

    //Testing if inside the bounds in empty space
    @Test 
    public void checkSpace1() {
        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        game.placeMarker(pos, 'X');


        System.out.println('\n'+game.toString()+'\n' + '\n');

        assert(game.checkSpace(pos));
    }

    // testing inside the bounds in occupied space
    @Test 
    public void checkSpace2() {
        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        assert(game.checkSpace(pos));
    }

    //testing input outside of the boards bounds
    @Test 
    public void checkSpace3() {
        IGameBoard game = gameB();


        BoardPosition pos = new BoardPosition(sizex,sizey);        

        assert(game.checkSpace(pos));
    }


//checkHorizontalWin
    //testing when horizontal row of markers grows from left to right
    @Test
    public void horizontalCheck1() {

        IGameBoard game = gameB();


        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition(4, i + 4);
            game.placeMarker(pos, 'X');
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    //testing when horizontal row of markers grows from right to left
    @Test
    public void horizontalCheck2() {

        IGameBoard game = gameB();


        BoardPosition pos = new BoardPosition(0,0);
        
        int randValForRow = 4;

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition(randValForRow, sizex - i);
            game.placeMarker(pos, 'X');
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    // Testing if places 2 Xs and then a O then 3 X
    @Test
    public void horizontalCheck3() {

        IGameBoard game = gameB();
        int randValForRow = 4;

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition(randValForRow, sizex - i);
            if (i == win - 2) {
                game.placeMarker(pos, 'O');
            } else {
                game.placeMarker(pos, 'X');
            }
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }
 
    //checkVerticalWin

    @Test
    public void verticalCheck1() {

        IGameBoard game = gameB();


        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition(i, 3);
            game.placeMarker(pos, 'X');
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    @Test
    public void verticalCheck2() {

        IGameBoard game = gameB();


        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition(20-i, 3);
            game.placeMarker(pos, 'X');
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    //checkDiagonalWin

    @Test
    public void diagonalWinCheckNESW1() {

        IGameBoard game = gameB();


        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition((sizey-10)-i,i+4);
            game.placeMarker(pos, 'X');
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    @Test
    public void diagonalWinCheckNESW1FlipDir() {

        IGameBoard game = gameB();


        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition(i +4, 7 - i);
            game.placeMarker(pos, 'X');
        }
       
        System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }


    @Test
    public void diagonalWinCheckNESW2() {

        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; win > i; i++ ) {
            pos = new BoardPosition((sizey-10)-i,i);
            if (i == 2) {
                game.placeMarker(pos, 'O');
                BoardPosition newPos = new BoardPosition((sizey-10)-win,win);   
                game.placeMarker(newPos, 'X');
     


            }
            else game.placeMarker(pos, 'X');
        }
        System.out.println("thot");

        System.out.println('\n'+game.toString());

        assert(game.checkForWinner(pos));
    }


    //checkForDraw

    //isPlayerAtPos

    //placeMarker

  
    // public void diagonalWinCheckNESW2() {

    //     IGameBoard game = new GameBoard(0,0,0);
    //     if (type == Implentation.FAST)
    //         game = new GameBoard(sizex,sizey,win);
    //     if (type == Implentation.MEM)
    //         game = new GameBoardMem(sizex,sizey,win);

    //     Random rand = new Random();

    //         // Obtain a number between [0 - 49].
    //     int max = ((game.getNumColumns >= game.getNumRows) ? (game.getNumColumns) : (game.getNumRows) - win);
    //     int n = rand.nextInt(50);

    //     int start = rand.nextInt(max);

    //     BoardPosition pos = new BoardPosition(0,0);        

    //     for (int i = 0; win > i; i++ ) {
    //         pos = new BoardPosition((sizey-10)-i,i);
    //         game.placeMarker(pos, 'X');
    //     }
       
    //     System.out.println('\n'+game.toString());

    //     assertTrue(game.checkForWinner(pos));
    // }

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

    @Test
    public void checkPos() {

        IGameBoard game = new GameBoard(0,0,0);
        if (type == Implentation.FAST)
            game = new GameBoard(sizex,sizey,win);
        if (type == Implentation.MEM)
            game = new GameBoardMem(sizex,sizey,win);

        BoardPosition pos = new BoardPosition(2,5);   
        game.placeMarker(pos, 'O');
    

        System.out.println('\n'+game.toString());

        System.out.println(pos.toString());

        assert(game.checkForWinner(pos));
    }


    // @Test
    // public void toStringTest() {

    //     IGameBoard game = new GameBoard(0,0,0);
    //     if (type == Implentation.FAST)
    //         game = new GameBoard(14,11,win);
    //     if (type == Implentation.MEM)
    //         game = new GameBoardMem(14,11,win);

    //     string test = ("
    //      0| 1| 2| 3| 4| 5| 6| 7| 8| 9|10|11|12|13|14|\n
    //     0| | | | | | | | | | | | | | | |\n
    //     1| | | | | | | | | | | | | | | |\n
    //     2| | | | | | | | | | | | | | | |\n
    //     3| | | | | | | | | | | | | | | |\n
    //     4| | | | | | | | | | | | | | | |\n
    //     5| | | | | | | | | | | | | | | |\n
    //     6| | | | | | | | | | | | | | | |\n
    //     7| | | | | | | | | | | | | | | |\n
    //     8| | | | | | | | | | | | | | | |\n
    //     9| | | | | | | | | | | | | | | |\n
    //     10| | | | | | | | | | | | | | | |\n
    //     11| | | | | | | | | | | | | | | |\n");
    //     System.out.print(test);
    // }

}