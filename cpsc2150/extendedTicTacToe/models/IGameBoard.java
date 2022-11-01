// Jake Macdonald 
package cpsc2150.extendedTicTacToe.models;
import java.lang.Math;
import cpsc2150.extendedTicTacToe.GameScreen;


/**
 * IGameBoard is an abstractly defined class that represents a 
 * tic-tac-toe gameboard 
 * 
 * Initialization ensures: GameBoard is able to represent 
 * a table of two dimensions containing characters. Each element 
 *  of the GameBoard initiates with a value of ' '. Additionally, 
 * the gameboard is a 5X8
 * 
 * Defines: Turns: Z AND NumRows: AND NumColumns: Z AND NumToWin: Z
 * 
 * Constraints: 0 <= Turns <= (NumRows * NumColumns) AND NumToWin = #NumToWin
 *  AND NumColumns = #NumColumns AND NumRows = #NumRows
 */
public interface IGameBoard {  
    /**
      * This function expresses what mark exists at a given position.
      *@return a char at the given position on the gameBoard. 
      * @param pos represents a board position.
      * @pre pos must adhere to conditions of BoardPosition
      * @post self = #self AND whatsAtPos = board[pos.getColumn()][pos.getRow()]
      */
      public char whatsAtPos (BoardPosition pos); 

    // /**
    //  * getTurnsPlayed provides value of Turns
    //  * 
    //  *@return integer count of Turns.

    //  * @post self = #self AND getTurnsPlayed() = Turns AND Turns = #Turns
    //  */

    // public int getTurnsPlayed();
      
    /**
     * provides value of NumRows 
     * 
     *@return integer count of NumRows.

     * @post self = #self AND getNumRows() = NumRows 
     */
    public int getNumRows();

        /**
     * provides value of NumColumns
     * 
     *@return integer count of NumColumns.

     * @post self = #self AND getNumColumns() = NumColumns 
     */
    public int getNumColumns();

         /**
     * provides value of NumToWIn
     * 
     *@return integer count of NumToWIn.

     * @post self = #self AND getNumToWin() = NumToWin 
     */
    public int getNumToWin();

           /**
      * This method places a given mark upon the board. 
      * @param marker represents a board position.
      * @param player signifies the mark symobol being placed on the board.
      * 
      * @pre [Marker must adhere to conditions of BoardPosition]
      *       AND [the marker space must not already be occupied
      *      (marker = 'X' OR marker = 'O')] AND Turns < (getNumColumns() * getNumRows())

      * @post Turns = #Turns + 1 AND self = #self, except @{code marker} will be 
        assigned value of @{code player} AND place  
      */
 
      public void placeMarker (BoardPosition marker, char player);

        /**
      * This function determines if the given position, pos exists on the board 
      * and is not occupied by an existing marker.
      *@return a boolean indicating the validity of pos. 
      * @param pos represents a board position.
      * 
      * @pre pos must adhere to conditions of BoardPosition
      * @post self = #self AND CheckSpace = (if pos an element of the board &&
      *  (board[pos.getColumn()][pos.getRow()] == ' '))
      */
     default public boolean checkSpace (BoardPosition pos) {
        if ((pos.getColumn() < getNumColumns() && pos.getColumn() >= 0)
             && (pos.getRow() < getNumRows() && pos.getRow() >= 0))
             if ((whatsAtPos(pos) == ' '))
                 return true;
         return false;
     }
 
 
 
 
     /**
      * This function determines if the given board position has been
      * marked with a player symbol.
      * @return a boolean indicating player presence at pos. 
      * @param pos represents a board position.
      * @param player represents a player mark symbol.
      * @pre lastPos must adhere to conditions of BoardPosition
      *      AND player = 'X' OR player = 'O'
      * @post self = #self AND isPlayerAtPos = 
      * (board[pos.getColumn()][pos.getRow()] == player)
      */
     default public boolean isPlayerAtPos (BoardPosition pos, char player) {
         return (whatsAtPos(pos) == player);
     }
    
      /**
     * This function determines if the board possesses a series of equal adjacent
     * markings of length winNeed. Expresses a winner.
     *@return a boolean indicating if a team has won. 
     * @param lastPos represents a board position.
     * @pre lastPos must adhere to conditions of BoardPosition AND 
     *      should be most recently placed item AND player must be occupying the space 
     * @post self = #self AND
     *      checkForWinner = (checkHorizontalWin(lastPos, player) OR
            checkVerticalWin(lastPos, player) OR
            checkDiagonalWin(lastPos)))
     */

    default public boolean checkForWinner(BoardPosition lastPos) {
        char player = whatsAtPos(lastPos);
        return (checkHorizontalWin(lastPos, player) ||
                checkVerticalWin(lastPos, player) ||
                checkDiagonalWin(lastPos, player));
    }

    /**
     * This function determines if the board possesses a horizontal
     * win condition.
     *@return a boolean indicating if horizontal win condition is true. 
     * @param lastPos represents a board position.
     * @pre lastPos must adhere to conditions of BoardPosition
     * @post self = #self AND checkHorizontallWin =
     *      (num of horizontally adjacent neighbors >= winNeed)
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int x = lastPos.getColumn();
        int y = lastPos.getRow();
        
        // start with one to include self.
        int count = 1; 

        //Stop looking in this direction once 
        boolean stopPos = false; 
        boolean stopNeg = false; 

        for (int j = 1; j <= getNumToWin() -1; j++) {
        
            int curX = x + (j);
            if (!stopPos)
                if (curX < getNumColumns())
                    if (whatsAtPos(new BoardPosition(y,curX)) == player) 
                        count++;
                    else
                        stopPos = true;
                    
            curX = x - (j);
            if (!stopNeg)
                if (curX >= 0)
                    if (whatsAtPos(new BoardPosition(y,curX)) == player) 
                        count++;
                    else stopNeg = true;
                    
           
            if (count >= getNumToWin()) return true;
        }
        return false;
    }

    /**
     * This function determines if the board possesses a vertical
     * win condition.
     *@return a boolean indicating if horizontal win condition is true. 
     * @param lastPos represents a board position.
     * @pre lastPos must adhere to conditions of BoardPosition
     * @post self = #self AND checkVerticalWin =
     *      (num of vertically adjacent neighbors >= winNeed)
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int x = lastPos.getColumn();
        int y = lastPos.getRow();

        // start with one to include self.
        int count = 1; 

        boolean stopPos = false; 
        boolean stopNeg = false; 

        for (int j = 1; j <= getNumToWin() - 1; j++) {
           
            int curY = y + (j);
            if (!stopPos)
                if (curY < getNumRows())
                    if (whatsAtPos(new BoardPosition(curY,x)) == player) 
                        count++;
                    else stopPos = true;
            
            curY = y - (j);
            if (!stopNeg)
                if (curY >= 0)
                    if (whatsAtPos(new BoardPosition(curY,x)) == player) 
                        count++;
                    else stopNeg = true;

            if (count >= getNumToWin()) return true;
        }
        
        return false;
    }

        /**
     * This function determines if the board possesses a diagonal
     * win condition.
     *@return a boolean indicating if horizontal win condition is true. 
     * @param lastPos represents a board position.
     * @pre lastPos must adhere to conditions of BoardPosition
     * @post self = #self AND checkDiagonalWin =
     *      [true if a placed marker 
     *      is the last to make up the target number of 
     *      consecutive same markers needed to win diagonally]
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int x = lastPos.getColumn();
        int y = lastPos.getRow();
    
        // starts by looking with negative slope, then forgoes 0 slope, 
        // then has positive growth.
        for (int i = -1; i <= 1 ; i++) {
            if (i == 0) continue;
            
            // start with one to include self.
            int count = 1; 

            for (int j = 1; j <= getNumToWin(); j++) {
                // k flips sign value, thus allows check in both directions
                boolean lookNeg = true;
                boolean lookPos = true;
                for (int k = -1; k <= 1 ; k++) {
                    if (k == 0) continue;
                    if (k > 0 && !lookNeg) continue;
                    if (k < 0 && !lookPos) continue;

                    int curX = (x + j * k);
                    int curY = (y + j * i * k);

                    if ((curX >= 0 && curX < getNumColumns()) 
                        && (curY >= 0 && curY < getNumRows())) {
                        if (whatsAtPos(new BoardPosition(curY,curX)) == player)
                                count++;
                        else  {
                            if (k > 0) lookPos = false;
                            else lookNeg = false;
                        }

                    }
                }
                if (count >= getNumToWin()) return true;
            }
        }
        return false;
    }

    /**
     * This function determines if all board possible board placements are occupied.
     *@return a boolean indicating occurence of draw. 
     * @post self = #self AND checkForDraw =
     *      [true if there exists no possible win condition for either team]
     */
    default public boolean checkForDraw() {
        int spacenum = 0;

        for (int i = 0; getNumColumns() > i; i++)
            for (int j = 0; getNumRows() > j; j++) {
                BoardPosition pos = new BoardPosition(j,i);
                 char val = whatsAtPos(pos);
                 if (val == ' ') spacenum++;
            }
        if (spacenum > 0) return false;
        return true;
    }
}