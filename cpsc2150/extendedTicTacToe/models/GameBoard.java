// Jake Macdonald 
package cpsc2150.extendedTicTacToe.models;

/**
 * class operating akin to qualities of a tic-tac-toe gameboard
 * @invariant  0 <= turnsplayed <= (colNum * rowNum) AND winNeed = #winNeed 
 *              And colNum = #colNum AND rowNum = #rowNum 
 * 
 * @correspondences Turns = turnsPlayed AND NumRows = rowNum AND 
 *  NumColumns = colNum AND NumToWin = winNeed AND self = board
 */

// GameBoard extends AbsGameBoard which implements IGameBoard
public class GameBoard extends AbsGameBoard{
    private int rowNum = 5;
    private int colNum = 8;
    private int winNeed = 5;
    private int turnsPlayed = 0;
    private char[][] board = new char [colNum][rowNum];

    /**
     * This constructor creates a gameboard defined in 2 dimensions of characters.
     * @post For all elements of board  = ' ' AND board = #board except [for all elements of board set to ' '] 
     */
    public GameBoard() {
        for (int i = 0; i < colNum; i++)
            for (int j = 0; j < rowNum; j++) {
                board[i][j] = ' ';
            }
    }

    /*
     * returns a value equal to winNeed
     */
    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    /*
     * returns a value equal to rowNum
     */
    public int getNumRows() {
        return rowNum;
    }

    /*
     * returns a value equal to colNum
     */
    public int getNumColumns() {
        return colNum;
    }

    /*
     * returns a value equal to winNeed
     */
    public int getNumToWin() {
        return winNeed;
    }

    /*
     * returns a two dimensional character array equal to board
     */

    public char whatsAtPos (BoardPosition pos) {
        return board[pos.getColumn()][pos.getRow()];
     }

    /*
     * Asigns value of @{code player} to the board position of {@code marker}
     */
    public void placeMarker (BoardPosition marker, char player) {
        if (checkSpace(marker)) {
            board[marker.getColumn()][marker.getRow()] = player;
            turnsPlayed++;
        }
    }
}