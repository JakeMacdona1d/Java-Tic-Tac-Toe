// Jake Macdonald 
package cpsc2150.extendedTicTacToe.models;

/**
 * An abstract class that contains an overridden 
 * implementation of {@link Object#toString()}
 */
public abstract class AbsGameBoard implements IGameBoard {

    /**
     * This method overrides the default implementation of {@code toString} to provide 
     * a string representation of the object.
     * 
     * @return a string representation of the GameBoard
     * 
     * @post toString = "
     *    0 1 2 3 4 5 6 7 
        0| | | | | | | | |
        1| | | | | | | | |
        2| | | | | | | | |
        3| | | | | | | | |
        4| | | | | | | | |
        where ' ' = 'X' OR ' ' = 'O'
        OR ' ' = ' '
        AND getNumColumns() = #getNumColumns() AND getNumRows() = #getNumRows() 
        AND getBoard() = #getBoard()
    */
    @Override
    public String toString() {
       String boardScheme = " ";
       int boardWidth = getNumColumns();
       int boardHeight = getNumRows();
       char[][] board = new char [getNumColumns()][getNumRows()];
       for (int i = 0; getNumRows() > i; i++)
           for (int j = 0; getNumColumns() > j; j++) {
               BoardPosition copy = new BoardPosition(i,j);
               board[j][i] = whatsAtPos(copy);
           }
        
       for (int i = 0; i < boardWidth; i++) {
            boardScheme += " " + Integer.toString(i);
       }
       boardScheme += "\n";

        for (int i = 0; i < boardHeight; i++) {
            boardScheme += Integer.toString(i);
            boardScheme += "|";
            for (int j = 0; j < boardWidth; j++) {
                boardScheme += board[j][i];
                boardScheme += "|";
            }
            boardScheme += "\n";
        }
        return boardScheme;
    }
}