package cpsc2150.extendedTicTacToe.models;
import org.junit.Test;
import static org.junit.Assert.*;
import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.IGameBoard;
import cpsc2150.extendedTicTacToe.models.GameBoard;
import cpsc2150.extendedTicTacToe.models.GameBoardMem;

public class TestGameBoard{


    private IGameBoard gameB(int y, int x, int z) {	
        return new GameBoard(y, x, z);
    }


    private String toString(char[][] gb) {
       String boardScheme = "   ";

       int boardHeight = gb.length;

       int boardWidth =  gb[0].length;
       
        
       for (int i = 0; i < boardWidth; i++) {
            if (i < 10) boardScheme += " ";
            boardScheme += Integer.toString(i) + "|";
       }
       boardScheme += "\n";

        for (int i = 0; i < boardHeight; i++) {
            if (i < 10)
                boardScheme+= " ";
            boardScheme += Integer.toString(i);
            boardScheme += "|";

            for (int j = 0; j < boardWidth; j++) {

                boardScheme += gb[j][i];
                boardScheme += " ";
                boardScheme += "|";
            }
            boardScheme += "\n";
        }
        return boardScheme;
    }


// Constructor
    //Tests smallest possible board dimmensions
    @Test
    public void constructor_small_board() {
        int x = 3;
        int y = 3;
        IGameBoard game = gameB(y,x,10);

        char expectedOutput[][] = new char[y][x];

        for (int i = 0; y > i; i++) {
            for (int j = 0; x > j; j++) {
                expectedOutput[i][j] = ' ';
            }
        }

        assertTrue(game.toString().equals(toString(expectedOutput)));

    }

    @Test
    public void constructor_small_win() {
        int x = 5;
        int y = 5;
        IGameBoard game = gameB(y,x,2);

        char expectedOutput[][] = new char[y][x];

        for (int i = 0; y > i; i++) {
            for (int j = 0; x > j; j++) {
                expectedOutput[i][j] = ' ';
            }
        }

        game.placeMarker(new BoardPosition(0, 0), 'X');

        expectedOutput[0][0] = 'X';

        game.placeMarker(new BoardPosition(1, 1), 'X');

        expectedOutput[1][1] = 'X';


        assertTrue(game.toString().equals(toString(expectedOutput)) && game.checkForWinner(new BoardPosition(1, 1)));
    }

    @Test
    public void constructor_large_board() {
        int x = 100;
        int y = 100;
        IGameBoard game = gameB(y,x,20);

        char expectedOutput[][] = new char[y][x];

        for (int i = 0; y > i; i++) {
            for (int j = 0; x > j; j++) {
                expectedOutput[i][j] = ' ';
            }
        }

       assertTrue(game.toString().equals(toString(expectedOutput)));
    }

//checkSpace

    //Testing if inside the bounds in filled space
    @Test 
    public void checkSpace_false_surrounded_by_space() {
        IGameBoard game = gameB(20,20,5);

        BoardPosition pos = new BoardPosition(2,2);        

        game.placeMarker(pos, 'X');


        assert(game.checkSpace(pos));
    }

    // testing inside the bounds in occupied space
    @Test 
    public void TestCheckSpace_true_surrounded_by_players() {
        IGameBoard game = gameB(20,20,5);

        for (int i = 0; 3 > i; i++) {
            BoardPosition posUp = new BoardPosition(1,1+i); 
            game.placeMarker(posUp, 'X');   
            
            BoardPosition posD = new BoardPosition(3,1+i); 
            game.placeMarker(posD, 'X');    
        }

        BoardPosition left = new BoardPosition(2,1);
        game.placeMarker(left, 'X');    

        
        BoardPosition right = new BoardPosition(2,3);   
        game.placeMarker(right, 'X');    
     

        BoardPosition check = new BoardPosition(2,2);    
        
        assertTrue(game.checkSpace(check));
    }

    //testing input outside of the boards bounds
    @Test 
    public void TestCheckSpace_false_outside_boundary() {
        IGameBoard game = gameB(20,20,5);

        BoardPosition pos = new BoardPosition(2,-2);        

        assert(game.checkSpace(pos));
    }


//checkHorizontalWin
    //testing when horizontal row of markers grows from left to right
    @Test
    public void TestCheckHorizontalWin_win_max_row() {

        IGameBoard game = gameB(100,100,25);


        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(game.getNumRows() / 2, i);
            game.placeMarker(pos, 'X');
        }
       
        assertTrue(game.checkForWinner(pos));
    }

    //testing when horizontal row of markers grows from right to left
    @Test
    public void TestCheckHorizontalWin_win_last_marker_middle() {     
        IGameBoard game = gameB(5,5,5);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(game.getNumRows()/2, game.getNumColumns() - i -1);
            if (i != game.getNumToWin() - 3) 
                game.placeMarker(pos, 'X');
        }

        pos = new BoardPosition(game.getNumRows()/2, 2);        

        game.placeMarker(pos, 'X');

        assertTrue(game.checkForWinner(pos));
    }



    // Testing if places 2 Xs and then a O then 3 X.
    //Tests to see if row counting continues after non-target element
    // is found.
    @Test
    public void TestCheckHorizontalWin_win_seperated_row() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() + 1 > i; i++ ) {
            pos = new BoardPosition(game.getNumRows()/2, game.getNumColumns() - i -1);
            if (i ==game.getNumToWin() - 3) {
                game.placeMarker(pos, 'O');
            } else game.placeMarker(pos, 'X');
        }

        assert(game.checkForWinner(pos));
    }


    //Tests horizontalgame.getNumToWin() on smallest board withgame.getNumToWin()Need = width = height = 3 
    @Test
    public void TestCheckHorizontalWin_win_size_of_board_() {

        IGameBoard game = gameB(3,3,3);
        int randValForRow = 1;

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; game.getNumToWin() > i; i++ ) {

            pos = new BoardPosition(randValForRow, i);
            game.placeMarker(pos, 'X');
        }
       
        assert(game.checkForWinner(pos));
    }
 
//checkVerticalWin

    // Tests when placing markers in ascending order. Board is set to standard. 
    @Test
    public void TestCheckVerticalWin_win_last_marker_middle() {
        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() +1 > i; i++ ) {
            pos = new BoardPosition(game.getNumRows() - i -1,game.getNumColumns()/2);
            if (i == game.getNumToWin() - 3) {
                continue;
            } else {
                if (i == game.getNumToWin() - 3) continue;
                game.placeMarker(pos, 'X');
            }
        }

     
        pos = new BoardPosition(3, game.getNumColumns()/2);

        game.placeMarker(pos, 'X');

        assertTrue(game.checkForWinner(pos));
    }

    // Tests when placing markers in descending order
    @Test
    public void TestCheckVerticalWin_no_win_seperated_row() {

        IGameBoard game = gameB(5,5,5);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(game.getNumRows() - i -1, game.getNumColumns()/2);
            if (i ==game.getNumToWin() - 3) {
                game.placeMarker(pos, 'O');
            } else {
                if (i == game.getNumToWin() - 3) continue;
                game.placeMarker(pos, 'X');
            }
        }

        assert(game.checkForWinner(pos));
    }

    //Places 2 Xs and then a O then 3 X.
    //Tests to see if row counting continues after non-target element
    // is found.
    @Test
    public void TestCheckVerticalWin_win_max_row() {

        IGameBoard game = gameB(25,25,25);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i, game.getNumColumns()/2);
            game.placeMarker(pos, 'X');
        }

        assert(game.checkForWinner(pos));
    }


    //Tests horizontalgame.getNumToWin() on smallest board withgame.getNumToWin()Need = width = height = 3 
    @Test
    public void TestCheckVerticalWin_win_size_of_board_() {

        IGameBoard game = gameB(3,3,3);
        int randValForRow = 1;
        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0; game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i, 1);
            game.placeMarker(pos, 'X');
        }
       
        assert(game.checkForWinner(pos));
    }

// checkDiagonalWin 8 tests
    @Test
    public void TestCheckDiagonallWin_win_NE_SW() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(game.getNumRows()- i -1 ,i);
            game.placeMarker(pos, 'X');
        }
       
        assertTrue(game.checkForWinner(pos));
    }

    @Test
    public void TestCheckDiagonallWin_win_SW_NE() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i, game.getNumColumns() -1 - i);
            game.placeMarker(pos, 'X');
        }
       
        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForWinner(pos));
    }

    //Places 2 Xs and then a O then the places (game.getNumToWin() - 2) X.
    //Tests to see if row counting continues after non-target element
    // is found.
    @Test
    public void TestCheckDiagonallWin_no_win_NE_SW_Interupt() {
        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(game.getNumRows()-1-i,i);
            if (i == 2) 
                game.placeMarker(pos, 'O');          
            else 
                game.placeMarker(pos, 'X');
        }

        game.placeMarker(new BoardPosition(0, 4),'X');
        // System.out.println('\n'+game.toString());

        assert(game.checkForWinner(pos));
    }

    // Tests row growing from SE to NW
    @Test
    public void TestCheckDiagonal_win_NW_SE() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(i,i);
            game.placeMarker(pos, 'X');
        }
       
        assertTrue(game.checkForWinner(pos));
    }


    // Tests row growing from SE to NW
    @Test
    public void TestCheckDiagonal_win_SE_NW() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);        

        for (int i = 0;game.getNumToWin() > i; i++ ) {
            pos = new BoardPosition(game.getNumRows() - i -1, game.getNumColumns() - i - 1);
            game.placeMarker(pos, 'X');
        }
        
        assertTrue(game.checkForWinner(pos));
    }


    // Tests row growing from NW to SE, with max board size and row win need.
    @Test
    public void TestCheckDiagonal_win_NW_SE_MAX() {

        IGameBoard game = gameB(5,5,4);

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
    public void TestCheckDiagonal_win_NW_SE_MIN() {

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
    public void TestCheckForDraw_full_board_() {

        IGameBoard game = gameB(5,5,5);

        BoardPosition pos = new BoardPosition(0,0);
        
        char alphabet = 'A';
        for (int i = 0; game.getNumRows() > i; i++)
            for (int j = 0; game.getNumColumns() > j; j++) {
                if (i == 2 && j == 2) continue;
                pos = new BoardPosition(i,j);
                game.placeMarker(pos, alphabet++);
            }

        // System.out.println('\n'+game.toString());

        assert(game.checkForDraw());
    }

    @Test
    public void TestCheckForDraw_full_board_MAX() {

        IGameBoard game = gameB(100,100,25);

        BoardPosition pos = new BoardPosition(0,0);
        
        char alphabet = 'A';
        for (int i = 0; game.getNumRows() > i; i++)
            for (int j = 0; game.getNumColumns() > j; j++) {
                pos = new BoardPosition(i,j);
                if (alphabet == 'z') 
                    alphabet = 'A';
                game.placeMarker(pos, alphabet++);
            }


        assertTrue(game.checkForDraw());
    }

    @Test
    public void TestCheckForDraw_Min_full_board() {

        IGameBoard game = gameB(3,3,3);

        BoardPosition pos = new BoardPosition(0,0);
        
        char alphabet = 'A';
        for (int i = 0; game.getNumRows() > i; i++)
            for (int j = 0; game.getNumColumns() > j; j++) {
                pos = new BoardPosition(i,j);
                if (alphabet == 'z') 
                    alphabet = 'A';
                game.placeMarker(pos, (alphabet++));
            }

        // System.out.println('\n'+game.toString());

        assertTrue(game.checkForDraw());
    }

    @Test
    public void TestCheckForDraw_almost_full_board_() {

        IGameBoard game = gameB(5,5,5);

        BoardPosition pos = new BoardPosition(0,0);
        
        char alphabet = 'A';
        for (int i = 0; game.getNumRows() > i; i++)
            for (int j = 0; game.getNumColumns() > j; j++) {
                if (i == 2 && j == 2) continue;
                pos = new BoardPosition(i,j);
                if (alphabet == 'z') 
                    alphabet = 'A';
                game.placeMarker(pos, (alphabet++));
            }


        assert(game.checkForDraw());
    }

// whatsAtPos testing
    @Test
    public void TestWhatsAtPos_bottom_right_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(game.getNumRows()-1,game.getNumColumns() - 1);   

        game.placeMarker(pos, 'O');


        assertTrue(game.whatsAtPos(pos) == 'O');
    }

    @Test
    public void TestWhatsAtPos_bottom_left_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(game.getNumRows() - 1,0);   

        game.placeMarker(pos, 'O');


        assertTrue(game.whatsAtPos(pos) == 'O');
    }

    @Test
    public void TestWhatsAtPos_top_left_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);   

        game.placeMarker(pos, 'O');


        assertTrue(game.whatsAtPos(pos) == 'O');
    }

    @Test
    public void TestWhatsAtPos_top_right_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,game.getNumColumns() - 1);   

        game.placeMarker(pos, 'O');


        assertTrue(game.whatsAtPos(pos) == 'O');
    }

    @Test
    public void TestWhatsAtPos_surrounded() {

        IGameBoard game = gameB(5,5,3);

        BoardPosition pos = new BoardPosition(0,0);   

        char alphabet = 'A';
        for (int i = 0; game.getNumRows() > i; i++)
            for (int j = 0; game.getNumColumns() > j; j++) {
                if (i == 2 && j == 2) continue;
                pos = new BoardPosition(i,j);
                if (alphabet == 'z') 
                    alphabet = 'A';
                game.placeMarker(pos, (alphabet++));
            }

        game.placeMarker(new BoardPosition(2,2), 'O');

        assertTrue(game.whatsAtPos(new BoardPosition(2,2)) == 'O');
    }

//isPlayerAtPos
    @Test
    public void TestisPlayerAtPos_is_space() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(2,2); 
        
        assert(game.isPlayerAtPos(pos,'X'));
    }


    @Test
    public void TestisPlayerAtPos_diff_token() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(2,2); 

        game.placeMarker(pos, 'O');
        
        assert(game.isPlayerAtPos(pos,'X'));
    }

    @Test
    public void TestWhatsAtPos_top_left_edge_empty() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);   


        assert(game.isPlayerAtPos(pos, 'O'));
    }

    @Test
    public void TestisPlayerAtPos_bottom_right_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(game.getNumRows()-1,game.getNumColumns() - 1);   

        game.placeMarker(pos, 'O');


        assertTrue(game.isPlayerAtPos(pos, 'O'));
    }

    @Test
    public void TestisPlayerAtPos_bottom_left_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,game.getNumColumns() - 1);   

        game.placeMarker(pos, 'O');


        assertTrue(game.isPlayerAtPos(pos, 'O'));
    }

    //PlaceMarker

    @Test
    public void TestPlaceMarker_top_right_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,game.getNumColumns() - 1);   

        game.placeMarker(pos, 'O');

        assertTrue(game.isPlayerAtPos(pos, 'O'));
    }


    @Test
    public void TestPlaceMarker_top_left_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(0,0);   

        game.placeMarker(pos, 'O');


        assertTrue(game.isPlayerAtPos(pos, 'O'));
    }

    
    @Test
    public void TestPlaceMarker_bottom_left_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(game.getNumRows() - 1,0);   

        game.placeMarker(pos, 'O');


        assertTrue(game.isPlayerAtPos(pos, 'O'));
    }


    @Test
    public void TestPlaceMarker_bottom_right_edge() {

        IGameBoard game = gameB(5,5,4);

        BoardPosition pos = new BoardPosition(game.getNumRows() - 1,game.getNumColumns() - 1);   

        game.placeMarker(pos, 'O');


        assertTrue(game.isPlayerAtPos(pos, 'O'));
    }


    @Test
    public void TestisPlaceMarker_surrounded_mid() {

        IGameBoard game = gameB(10,10,5);

        for (int i = 0; 3 > i; i++) {
            BoardPosition posUp = new BoardPosition(1,1+i); 
            game.placeMarker(posUp, 'X');   
            
            BoardPosition posD = new BoardPosition(3,1+i); 
            game.placeMarker(posD, 'X');    
        }

        BoardPosition left = new BoardPosition(2,1);
        game.placeMarker(left, 'X');    

        BoardPosition right = new BoardPosition(2,3);   
        game.placeMarker(right, 'X');    

        BoardPosition target = new BoardPosition(2,2);    

        game.placeMarker(target, 'O');

        assertTrue(game.isPlayerAtPos(target, 'O'));
    }

}