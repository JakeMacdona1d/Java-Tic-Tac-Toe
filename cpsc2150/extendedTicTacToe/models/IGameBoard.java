// Jake Macdonald 
package cpsc2150.extendedTicTacToe.models;

/**
 * IGameBoard is an abstractly defined class that represents a 
 * tic-tac-toe gameboard 
 * 
 * Initialization ensures: GameBoard is able to represent 
 * a table of two dimensions containing characters. Each element 
 *  of the GameBoard initiates with a value of ' '. Additionally, 
 * the gameboard is of dimensions A X B, such that A and B are elements
 * of ALL integers in the range [MIN_DIMEN, MAX_DIMEN]. Further, the number of rows should
 * be set within the range [MIN_WIN, MAX_WIN].
 * 
 * Defines: NumRows : Z AND NumColumns: Z AND NumToWin: Z
 * 
 * Constraints: NumToWin = #NumToWin
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
      *      (marker equals any capitalized alphabetical character)]

      * @post self = #self, except @{code marker} will be 
        assigned value of @{code player}  
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
        // I lost points on project II for "checking if precondition is true."
        // However, in the project 1 report, the checkSpace function is given the
        //description :
        //returns true if the position specified in pos is available;
        //false otherwise. If a space is not in bounds, then it is not available

        // Thus the following statements are correct.
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
      *      AND player = [Any capitalized alphabetical character]
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

            boolean lookNeg = true;
                boolean lookPos = true;

            for (int j = 1; j <= getNumToWin(); j++) {
                // k flips sign value, thus allows check in both directions
                
                for (int k = -1; k <= 1 ; k++) {
                    if (k == 0) continue;
                    if (k > 0 && !lookPos) continue;
                    if (k < 0 && !lookNeg) continue;

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
     * This function determines if no win condition exists.
     *@return a boolean indicating occurence of draw. 
     * @post self = #self AND checkForDraw =
     *      [true if no player can possibly win]
     */
    default public boolean checkForDraw() {
        char symbols[] = new char[10];
        int symnum = 0;
        
        //its basically a dictionary
        int spaces[][] = new int[getNumColumns() * getNumRows()][2];
        int spacenum = 0;
    
        // gets player tokens
        for (int i = 0; getNumColumns() > i; i++)
            for (int j = 0; getNumRows() > j; j++) {
                BoardPosition pos = new BoardPosition(j,i);
                 char val = whatsAtPos(pos);
                 if (val != ' ') {
                    boolean newChar = true;
                    for (int k = 0; symnum > k; k++)
                        if (symbols[k] == val) {
                            newChar = false;
                            break;
                        }
                    if (newChar) {
                        symbols[symnum++] = val;
                    }
                 } else {
                    spaces[spacenum][0] = j;
                    spaces[spacenum++][1] = i; 
                 }
            }
    
        for (int i = 0; symnum > i; i++) {
            BoardPosition pos = new BoardPosition(0,0);
            boolean draw = true;
            for (int j = 0; spacenum > j; j++) {
                pos = new BoardPosition(spaces[j][0],spaces[j][1]);
                placeMarker(pos, symbols[i]);
                if (checkForWinner(pos)) {
                    draw = false;
                    break;
                }
            }
            if (draw) return true;
            for (int j = 0; spacenum > j; j++) {
                pos = new BoardPosition(spaces[j][0],spaces[j][1]);
                placeMarker(pos, ' ');
            }
    
        }
    
        return false;
    }    
}