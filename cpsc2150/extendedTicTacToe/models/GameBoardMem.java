// Jake Macdonald 
package cpsc2150.extendedTicTacToe.models;
import java.util.*;


/**
 * class operating akin to qualities of a tic-tac-toe gameboard
 * @invariant  0 <= colNum * rowNum) AND winNeed = #winNeed 
 *              And colNum = #colNum AND rowNum = #rowNum 
 * 
 * @correspondences self = board[0..MAX_ROW_NUM-1][0..MAX_COLUMN_NUM-1]
 */

// GameBoard extends AbsGameBoard which implements IGameBoard
public class GameBoardMem extends AbsGameBoard implements IGameBoard{
    private int rowNum;
    private int colNum;
    private int winNeed;
    private Map<Character, ArrayList<BoardPosition>> hm;

    /**
     * This constructor creates a gameboard defined in 2 dimensions of characters.
     * @post For all elements of board  = ' ' AND board = #board except [for all elements of board set to ' '] 
     */
    public GameBoardMem(int col, int row, int winNum) {
        colNum = col;
        rowNum = row;
        winNeed = winNum;
        hm = new HashMap<Character, ArrayList<BoardPosition>>();
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
        
        for (int i = 0; hm.size() > i; i++) {
			char key = hm.keySet().toArray()[i].toString().charAt(0);
			for (int j = 0; hm.get(key).size() > j; j++) {
                if (hm.get(key).get(j).getRow() == pos.getRow() 
                    && hm.get(key).get(j).getColumn() == pos.getColumn())
                    return key;
			}
		}

        return ' ';
     }

     @Override
    public boolean isPlayerAtPos (BoardPosition pos, char player) {
        for (int i = 0; hm.get(player).size() > i; i++) {
            if (hm.get(player).get(i).getRow() == pos.getRow() 
                && hm.get(player).get(i).getColumn() == pos.getColumn())
                return true;
        }
        return false;
    }

    /*
     * Asigns value of @{code player} to the board position of {@code marker}
     */
    public void placeMarker (BoardPosition marker, char player) {
        if (Objects.isNull(hm.get(player))) {
            hm.put(player, new ArrayList<BoardPosition>());
        }


		hm.get(player).add(new BoardPosition(marker.getRow(),marker.getColumn()));
    }
}