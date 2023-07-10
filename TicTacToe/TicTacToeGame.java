import Model.*;

import java.util.*;

import Model.Board;
import Model.Player;

// Simple Entry is used as an alternative to Class Pair
import java.util.AbstractMap.SimpleEntry;


public class TicTacToeGame {

    Deque<Player> players;
    Board gameBoard;

    TicTacToeGame(){
        initializeGame();
    }

    private void initializeGame(){
        //creating 2 players
        players = new LinkedList<>();

        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player1", crossPiece);

        PlayingPieceO circlePiece = new PlayingPieceO();
        Player player2 = new Player("Player2", circlePiece);

        players.add(player1);
        players.add(player2);

        // initialize board
        gameBoard = new Board(3);
    }

    public String startGame() {
        boolean noWinner = true;

        while (noWinner) {

            // take the first player from the deque list
            Player playerTurn = players.removeFirst();

            // get the free space form the board
            gameBoard.printBoard();
            List<SimpleEntry<Integer, Integer>> freeSpaces = gameBoard.getFreeCells();
            if (freeSpaces.isEmpty()) {
                // no free space is available
                noWinner = false;
                continue;
            }

            // free space is available
            // read the user input
            System.out.println("Player: " + playerTurn.name + " Enter row and col: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputcol = Integer.valueOf(values[1]);

            // place the piece
            boolean pieceAddedSuccessfully = gameBoard.addPiece(inputRow, inputcol, playerTurn.playingPiece);
            if (!pieceAddedSuccessfully) {
                // player cannot enter the piece in the cee, player has to choose other cell
                System.out.println("Incorrect position chooses, please try again!!");
                players.addFirst(playerTurn); // adding in the first position
                continue;
            }

            players.addLast(playerTurn); // add in last, coz player has choosen correct pos and its another player turn

            boolean winner = isThereWinner(inputRow, inputcol, playerTurn.playingPiece.pieceType);
            if (winner) {
                return playerTurn.name;
            }
        }
        return "tie";
    }

    // this code logic can be improved -> using NQueenLogic -> O(1) I guess
    private boolean isThereWinner(int row, int col, PieceType pieceType){
        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //check in row
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //check in column
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[i][col] == null || gameBoard.board[i][col].pieceType != pieceType) {
                columnMatch = false;
            }
        }

        //check diagonals
        for(int i=0, j=0; i<gameBoard.size;i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //check anti-diagonals
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size;i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }
}
