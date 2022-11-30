// Jake Macdonald 
package guiV.cpsc2150.extendedTicTacToe.models;

/**
 * class operating akin to qualities of a tic-tac-toe gameboard
 * @invariant winNeed = #winNeed And colNum = #colNum AND rowNum = #rowNum 
 *            AND rowNum = [MIN_ROW_NUM, MAX_ROW_NUM] AND colNum = [MIN_COL_NUM, MAX_COL_NUM]
 *            AND winNeed = [MIN_WIN_NUM, MAX_WIN_NUM]
 * 
 * @correspondences self = board[0..MAX_ROW_NUM-1][0..MAX_COLUMN_NUM-1]
 */

// GameBoard extends AbsGameBoard which implements IGameBoard
public class GameBoard extends AbsGameBoard implements IGameBoard{
    private int rowNum;
    private int colNum;
    private int winNeed;
    private char[][] board;

    /**
     * This constructor creates a gameboard defined in 2 dimensions of characters.
     * @param row represents the number of rows present on the board.
     * @param col represents the number of columns present on the board.
     * @param winNum represents the value of the winNeed state for the board.
     * 
     * @pre row = [MIN_ROW_NUM, MAX_ROW_NUM] AND col = [MIN_COL_NUM, MAX_COL_NUM]
     *             AND winNum = [MIN_WIN_NUM, MAX_WIN_NUM]
     * @post For all elements of board  = ' ' AND board = #board except [for all elements of board set to ' '] 
     *      AND values of row, col, and winNum are stored in corresponding private variables.
     */
    public GameBoard(int row, int col, int winNum) {
        colNum = col;
        rowNum = row;
        winNeed = winNum;
        board = new char[colNum][rowNum];

        for (int i = 0; i < colNum; i++)
            for (int j = 0; j < rowNum; j++) {
                board[i][j] = ' ';
            }
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
        board[marker.getColumn()][marker.getRow()] = player;
    }
}