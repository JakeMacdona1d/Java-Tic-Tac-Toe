// Jake Macdonald 
package cpsc2150.extendedTicTacToe;
import java.util.*;
import cpsc2150.extendedTicTacToe.models.BoardPosition;
import cpsc2150.extendedTicTacToe.models.GameBoard;
import cpsc2150.extendedTicTacToe.models.GameBoardMem;
import cpsc2150.extendedTicTacToe.models.IGameBoard;



public class GameScreen {

    public static int lowerBoundPos = 3;
    public static int upperBoundPos = 100;

    public static int lowerBoundW = 3;
    public static int upperBoundW = 25;

    public static int lowerBoundP = 2;
    public static int upperBoundP = 10;

    private static int setPlayerCount(Scanner scanner) {
        int playerCount = 0;
        while(playerCount == 0) {
            System.out.println("How many players?");

            int temp = scanner.nextInt();

            if (temp >= lowerBoundP && temp <= upperBoundP)
                playerCount = temp;
            else {
                if (temp > upperBoundP)
                    System.out.println("Must be 10 players or fewer");
                else
                    System.out.println("Must be at least 2 players");
            }  
        }
        return playerCount;
    }

    private static char[] setPlayers(Scanner scanner, int playerCount) {
        char[] players = new char[playerCount];
        for (int i = 0; playerCount > i; i++) {
            char temp;
            while (true) {
                boolean setVal = true;
                System.out.println("Enter the character to represent player " + Integer.toString(i + 1));
                temp = Character.toUpperCase(scanner.next().charAt(0)); 
                for (int j = 0; i > j; j++) {
                    if (players[j] == temp) {
                        System.out.println(players[j] +" is already taken as a player token!");
                        setVal = false;
                        break;
                    }
                }
                if (setVal) break;
            }
            players[i]  = temp;
        }
        return players;
    }

    private static int setRow(Scanner scanner) {
        int row = 0;
        while(row == 0) {
            System.out.println("How many rows?");

            int temp = scanner.nextInt();

            if (temp >= lowerBoundPos && temp <= upperBoundPos)
                row = temp;
            else  System.out.println("Rows must be between " +
            Integer.toString(lowerBoundPos)+" and "+Integer.toString(upperBoundPos));

        }

        return row;
    }
    
    private static int setColumn(Scanner scanner) {
        int col = 0;
        while(col == 0) {
            System.out.println("How many columns?");

            int temp = scanner.nextInt();

            if (temp >= lowerBoundPos && temp <= upperBoundPos)
                col = temp;
            else  System.out.println("Columns must be between " +
            Integer.toString(lowerBoundPos)+" and "+Integer.toString(upperBoundPos));

        } 
        return col;
    }

    private static int setWinNeed(Scanner scanner, int row, int col) {
        int winNeed = 0;
        while(winNeed == 0) {
            System.out.println("How many in a row to win?");

            int temp = scanner.nextInt();

            int low = (col >= row) ? (col) : (row);
            System.out.println(Integer.toString(low));

            if (temp >= lowerBoundP && temp <= upperBoundW && temp <= low)
                winNeed = temp;
            else System.out.println("Value must be in range of ["+
            Integer.toString(lowerBoundW)+","+Integer.toString(upperBoundW)+"]" +
            " and fit the board");

        } 

        return winNeed;
    }

    private static char setGameType (Scanner scanner) {
        char gameType = 0;

        while (gameType == 0) {
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?");

            char temp = Character.toUpperCase(scanner.next().charAt(0));

            if (temp == 'F' || temp == 'M')
                gameType = temp; 
        }
        return gameType;
    }
    public static void main(String[] args) {

        // Creates an object that will read in data from the command line.
        Scanner scanner = new Scanner(System.in);
        boolean encore = true;


        while (encore) { 
            int playerCount = setPlayerCount(scanner);
            char[] players = setPlayers(scanner, playerCount);
            int row = setRow(scanner);
            int col = setColumn(scanner);
            int winNeed = setWinNeed(scanner, row, col);
            char gameType = setGameType(scanner);


            ////////////////////
            IGameBoard game = new GameBoard(0,0,0);

            if (gameType == 'F')
                game = new GameBoard(col,row,winNeed);
            
            if (gameType == 'M')
                game = new GameBoardMem(col,row,winNeed);

            
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