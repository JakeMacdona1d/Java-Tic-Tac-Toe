// Jake Macdonald 
package guiV.cpsc2150.extendedTicTacToe.models;


public class BoardPosition{

    /**
 * A simple class to contain information about a GameBoard position.
 * 
 * @invariant Row >= 0 AND Column >=0
 */

    private int Row;
    private int Column;

    /**
     * This constructor creates a board location defined in 2 dimensions.
     * 
     * @param x represents the Row position
     * @param y represents the Column position
     * 
     * @pre x >= 0 AND y >=0
     * @post Row = x AND Column = y
     */
    public BoardPosition(int x, int y) {
        Row = x;
        Column = y;
    }


    /**
     * Gets the row value for the object instance
     * 
     * @return the Row value
     * 
     * @post getRow = Row AND Row = #Row
     */
    public int getRow() {
        return Row;
    }

     /**
     * Gets the Column value for the object instance
     * 
     * @return the Column value
     * 
     * @post getColumn = Column AND Column = #Column
     */
    public int getColumn() {
        return Column;
    }

    /**
     * Overrides the default implementation of {@code equals} to 
     * indicates if this object instance equals the given object instance
     * in respect to member values. 
     * 
     * @return bool value expressing if the BoardPosition's are equal 
     * 
     * @post equals = (o.getColumn() == Column && o.getRow() == Row)
     *  AND Row = #Row AND Column = #Column
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BoardPosition)) {
            return false;
        }
        BoardPosition compare = (BoardPosition)o;
        return (compare.getColumn() == Column && compare.getRow() == Row);
    }

    /**
     * This method overrides the default implementation of {@code toString} to provide 
     * a string representation of the object.
     * 
     * @return a string representation of the position
     * 
     * @post toString = "[Row], [Column] AND
	 *       Row = #Row AND Column = #Column 
     */
    @Override
    public String toString() {
        String s = Row + "," + Column; 
        return s;
    }
}