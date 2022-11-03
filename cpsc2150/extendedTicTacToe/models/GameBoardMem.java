// Jake Macdonald 
package cpsc2150.extendedTicTacToe.models;
import java.util.*;

/**
 * class operating akin to qualities of a tic-tac-toe gameboard
 * @invariant  0 <= colNum * rowNum) AND winNeed = #winNeed 
 *              And colNum = #colNum AND rowNum = #rowNum 
 * 
 * @correspondences self = board[Unique Keys linked to a corresponding ArrayList of BoardPositions]
 */

// GameBoard extends AbsGameBoard which implements IGameBoard
public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    private int rowNum;
    private int colNum;
    private int winNeed;
    private Map<Character, ArrayList<BoardPosition>> board;

    /**
     * This constructor creates a gameboard defined in 2 dimensions of characters.
     * @post For all elements of board  = ' ' AND board = #board except [for all elements of board set to ' '] 
     */
    public GameBoardMem(int col, int row, int winNum) {
        colNum = col;
        rowNum = row;
        winNeed = winNum;
        board = new HashMap<Character, ArrayList<BoardPosition>>();
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
        for (int i = 0; board.size() > i; i++) {
			char key = board.keySet().toArray()[i].toString().charAt(0);
			for (int j = 0; board.get(key).size() > j; j++) {
                if (board.get(key).get(j).getRow() == pos.getRow() 
                    && board.get(key).get(j).getColumn() == pos.getColumn())
                    return key;
			}
		}

        return ' ';
     }

     /**
     * This method overrides the default implementation of isPlayerAtPos to provide 
     * a more time efficient solution.
     * 
     * @return a boolean value indicating if given player exists at given BoardPosition
    * @param pos represents a board position.
    * @param player represents a player mark symbol.
    * @pre lastPos must adhere to conditions of BoardPosition
    *      AND player = 'X' OR player = 'O'
    * @post self = #self AND isPlayerAtPos = 
    * (board[pos.getColumn()][pos.getRow()] == player)
    **/

     @Override
    public boolean isPlayerAtPos (BoardPosition pos, char player) {
        for (int i = 0; board.get(player).size() > i; i++) {
            if (board.get(player).get(i).getRow() == pos.getRow() 
                && board.get(player).get(i).getColumn() == pos.getColumn())
                return true;
        }
        return false;
    }

    /*
     * Asigns value of @{code player} to the board position of {@code marker}
     */
    public void placeMarker (BoardPosition marker, char player) {
        if (Objects.isNull(board.get(player))) {
            board.put(player, new ArrayList<BoardPosition>());
        }
		board.get(player).add(new BoardPosition(marker.getRow(),marker.getColumn()));
    }
}