// Jake Macdonald 
package cpsc2150.extendedTicTacToe;
import java.util.*;
import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.GameBoard;

public class GameScreen {

    public static int lowerBoundPos = 3;
    public static int upperBoundPos = 100;

    public static int lowerBoundW = 3;
    public static int upperBoundW = 25;

    public static int lowerBoundP = 2;
    public static int upperBoundP = 10;

    public static void main(String[] args) {

        // Creates an object that will read in data from the command line.
        Scanner scanner = new Scanner(System.in);
        boolean encore = true;

        


        while (encore) { 

            int col = 0;
            int row = 0;
            int winNeed = 0;
            int playerCount = 0;


            do {
                System.out.println("Enter the number of columns on the gameboard");

                int temp = scanner.nextInt();

                if (temp >= lowerBoundPos && temp <= upperBoundPos)
                    col = temp;
                else  System.out.println("Column number must be in range of ["+
                Integer.toString(lowerBoundPos)+","+Integer.toString(upperBoundPos)+"]");

            } while(col == 0);

            do {
                System.out.println("Enter the number of rows on the gameboard");

                int temp = scanner.nextInt();

                if (temp >= lowerBoundPos && temp <= upperBoundPos)
                    row = temp;
                else  System.out.println("Row number must be in range of ["+
                Integer.toString(lowerBoundPos)+","+Integer.toString(upperBoundPos)+"]");

            } while(row == 0);
            
            do {
                System.out.println("number of markers needed to win");

                int temp = scanner.nextInt();

                int low = (col >= row) ? (col) : (row);
                System.out.println(Integer.toString(low));

                if (temp >= lowerBoundP && temp <= upperBoundW && temp <= low)
                    winNeed = temp;
                else  System.out.println("Value must be in range of ["+
                Integer.toString(lowerBoundW)+","+Integer.toString(upperBoundW)+"]" +
                " and fit the board");

            } while(winNeed == 0);

            do {
                System.out.println("number of players");

                int temp = scanner.nextInt();

                if (temp >= lowerBoundP && temp <= upperBoundP)
                    playerCount = temp;
                else  System.out.println("Value number must be in range of ["+
                Integer.toString(lowerBoundP)+","+Integer.toString(upperBoundP)+"]");

            } while(playerCount == 0);

            char[] players = new char[playerCount];

            for (int i = 0; playerCount > i; i++) {
                System.out.println("Enter player " + Integer.toString(i) +"'s symbol");
                char temp = Character.toUpperCase(scanner.next().charAt(0)); 
                for (int j = 0; i > j; j++) {
                    if (players[j] == temp) {
                        System.out.println("player symbol " + players[j] +" has already been selected."
                        +"\nPlease enter a unique symbol.");
                    }
                }
                players[i]  = temp;
            }

            ////////////////////

            GameBoard game = new GameBoard(col,row,winNeed);
            boolean winner = false;
            boolean draw = false;


            int turnsPlayed = 0;
           do {
                BoardPosition pos = new BoardPosition(0, 0);
                boolean error = false;
                do {
                    if (error) {
                        System.out.println("That space is unavailble,"
                            + " please pick again");
                    }
                    // either a 1 or 0, matches player array
                    System.out.println("Player " + players[turnsPlayed%playerCount]
                        + " Please enter your ROW");

                    int y = scanner.nextInt();

                    System.out.println("Player " + players[turnsPlayed%playerCount]
                        + " Please enter your COLUMN");

                    int x = scanner.nextInt();
                    pos = new BoardPosition(y, x); 
                    error = true;
                } while (!game.checkSpace(pos));

                game.placeMarker(pos, players[turnsPlayed%playerCount]);
                System.out.println(game.toString());
                winner = game.checkForWinner(pos);
                draw = game.checkForDraw();
                turnsPlayed++;
            } while (!winner && !draw);
            if (winner)
                System.out.println("Player " + players[(turnsPlayed-1) % playerCount] + " wins!");
            else if (draw)
                System.out.println("Draw game!");


            char repeatInput;
            boolean error = false;
            do {
                if (error) {
                    // Output for this scenario is not provided in project2
                    // on canvas
                    System.out.println("Invalid input, please re-enter");
                }
                error = true;                
                System.out.println("Would you like to play again? Y/N");
                repeatInput = scanner.next().charAt(0);
            } while (!(repeatInput == 'n' || repeatInput == 'N'
                || repeatInput == 'y' || repeatInput == 'Y'));
            
            if (repeatInput == 'n' || repeatInput == 'N') {
                encore = false;
            }
        }

       scanner.close();
    }
}