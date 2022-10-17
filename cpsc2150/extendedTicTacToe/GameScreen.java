// Jake Macdonald 
package cpsc2150.extendedTicTacToe;
import java.util.*;
import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.GameBoard;

public class GameScreen {

    public static void main(String[] args) {

        // Creates an object that will read in data from the command line.
        Scanner scanner = new Scanner(System.in);
        char[] player = {'X','O'};
        boolean encore = true;

        while (encore) { 
            GameBoard game = new GameBoard();
            boolean winner = false;

            while (!winner) {
                BoardPosition pos = new BoardPosition(0, 0);
                boolean error = false;
                do {
                    if (error) {
                        System.out.println("That space is unavailble,"
                            + " please pick again");
                    }
                    // either a 1 or 0, matches player array
                    System.out.println("Player " + player[game.getTurnsPlayed() %2]
                        + " Please enter your ROW");

                    int y = scanner.nextInt();

                    System.out.println("Player " + player[game.getTurnsPlayed()%2]
                        + " Please enter your COLUMN");

                    int x = scanner.nextInt();
                    pos = new BoardPosition(x, y); 
                    error = true;
                } while (!game.checkSpace(pos));

                game.placeMarker(pos, player[game.getTurnsPlayed()%2]);
                System.out.println(game.toString());
                winner = game.checkForWinner(pos);
            }

            System.out.println("Player " + player[(game.getTurnsPlayed() -1) % 2] + " wins!");

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