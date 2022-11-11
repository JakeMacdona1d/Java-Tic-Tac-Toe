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

    private IGameBoard gameB(int x, int y, int z) {	
        return new GameBoard(x, y, z);
    }

    enum Implentation {FAST,MEM};
    Implentation type = Implentation.FAST;
   


    // Constructor


//checkSpace

    //Testing if inside the bounds in filled space
    @Test 
    public void checkSpace1() {
        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        game.placeMarker(pos, 'X');


        // System.out.println('\n'+game.toString()+'\n' + '\n');

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

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(4, i + 4);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    //testing when horizontal row of markers grows from right to left
    @Test
    public void horizontalCheck2() {

        IGameBoard game = gameB();


        BoardPosition pos = new BoardPosition(0,0);
        
        int randValForRow = 4;

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(randValForRow, sizex - i - 1);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    // Testing if places 2 Xs and then a O then 3 X.
    //Tests to see if row counting continues after non-target element
    // is found.
    @Test
    public void horizontalCheck3() {

        IGameBoard game = gameB();
        int randValForRow = 4;

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() +1 > i; i++ ) {
            pos = new BoardPosition(randValForRow, sizex - i -1);
            if (i ==game.getNumToWin() - 3) {
                game.placeMarker(pos, 'O');
            } else {
                if (i == game.getNumToWin() - 3) continue;
                game.placeMarker(pos, 'X');
            }
        }
        
        // pos = new BoardPosition(randValForRow, sizex - (game.getNumToWin() - 2) -1);

        // System.out.println('\n'+game.toString());

        assert(game.checkForWinner(pos));
    }

    //Tests horizontalgame.getNumToWin() on smallest board withgame.getNumToWin()Need = width = height = 3 
    @Test
    public void horizontalCheck4() {

        IGameBoard game = gameB(3,3,3);
        int randValForRow = 1;

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; game.getNumToWin() > i; i++ ) {

            pos = new BoardPosition(randValForRow, i);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assert(game.checkForWinner(pos));
    }
 
//checkVerticalWin

    // Tests when placing markers in ascending order. Board is set to standard. 
    @Test
    public void verticalCheck1() {

        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i, 3);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    // Tests when placing markers in descending order. Board is set to standard. 
    @Test
    public void verticalCheck2() {

        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition((game.getNumRows()/2)-i, 3);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    //Places 2 Xs and then a O then 3 X.
    //Tests to see if row counting continues after non-target element
    // is found.
    @Test
    public void verticalCheck3() {

        IGameBoard game = gameB();
        int randValForRow = 4;

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() + 1 > i; i++ ) {
            pos = new BoardPosition(game.getNumRows() - i -1, game.getNumColumns()/2);
            if (i ==game.getNumToWin() - 2) {
                game.placeMarker(pos, 'O');
            } else {
                game.placeMarker(pos, 'X');
            }
        }
       
        // System.out.println('\n'+game.toString());

        assert(game.checkForWinner(pos));
    }


    //Tests horizontalgame.getNumToWin() on smallest board withgame.getNumToWin()Need = width = height = 3 
    @Test
    public void verticalCheck4() {

        IGameBoard game = gameB(3,3,3);
        int randValForRow = 1;
        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i, 1);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assert(game.checkForWinner(pos));
    }

// checkDiagonalWin
    @Test
    public void diagonalWinCheckNESW() {

        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition((sizey-10)-i,i+4);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    @Test
    public void diagonalWinCheckSWNE() {

        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i +4, 7 - i);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    //Places 2 Xs and then a O then the places (game.getNumToWin() - 2) X.
    //Tests to see if row counting continues after non-target element
    // is found.
    @Test
    public void diagonalWinCheckNESWCheckInterupt() {
        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition((sizey-10)-i,i);
            if (i == 2) {
                game.placeMarker(pos, 'O');
                BoardPosition newPos = new BoardPosition((sizey-10)-game.getNumToWin(),game.getNumToWin());   
                game.placeMarker(newPos, 'X');
    
            }
            else game.placeMarker(pos, 'X');
        }

        // System.out.println('\n'+game.toString());

        assert(game.checkForWinner(pos));
    }

    // Tests row growing from SE to NW
    @Test
    public void diagonalWinCheckNWSW() {

        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i,i+4);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }


    // Tests row growing from SE to NW
    @Test
    public void diagonalWinCheckSENW() {

        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(game.getNumRows() - i -1, game.getNumColumns() - i - 1);
            game.placeMarker(pos, 'X');
        }
        
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    //Edge case for largest extreme
    @Test
    public void diagonalWinCheckNWSE() {

        IGameBoard game = gameB(100,100,25);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i,i);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    // Tests row growing from NW to SE, with max board size and row win need.
    @Test
    public void diagonalWinCheckNWSEMAX() {

        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i,i);
            game.placeMarker(pos, 'X');
        }
        
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }


    // Tests row growing from NW to SE, with max board size and row win need.
    @Test
    public void diagonalWinCheckNWSEMIN() {

        IGameBoard game = gameB(3,3,3);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i,i);
            game.placeMarker(pos, 'X');
        }
        
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

// test for draw
    @Test
    public void drawCheck() {

        IGameBoard game = gameB(3,3,3);

        if (type == Implentation.FAST)
            game = new GameBoard(sizex,sizey,win);
        if (type == Implentation.MEM)
            game = new GameBoardMem(sizex,sizey,win);
        BoardPosition pos = new BoardPosition(0,0);        
      
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }


// whatsAtPos testing
    @Test
    public void checkPos() {

        IGameBoard game = new GameBoard(0,0,0);
        if (type == Implentation.FAST)
            game = new GameBoard(sizex,sizey,game.getNumToWin());
        if (type == Implentation.MEM)
            game = new GameBoardMem(sizex,sizey,game.getNumToWin());

        BoardPosition pos = new BoardPosition(2,5);   
        game.placeMarker(pos, 'O');
    

        // System.out.println('\n'+game.toString());

        // System.out.println(pos.toString());

        assert(game.checkForWinner(pos));
    }

//isPlayerAtPos testing
    @Test
    public void isPlayerAtPosTest() {
        IGameBoard game = gameB();

        BoardPosition pos = new BoardPosition(1,1);        

        game.placeMarker(pos, 'X');
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.isPlayerAtPos(pos,'X'));
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