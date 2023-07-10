package Model;


import java.util.ArrayList;
import java.util.List;

// Simple Entry is used as an alternative to Class Pair
import java.util.AbstractMap.SimpleEntry;

public class Board {

    public int size;
    public PlayingPiece[][]board;

    public Board(int size){
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece){
        if(board[row][col] != null)
            return false;
        board[row][col] = playingPiece;
        return true;
    }

    public List<SimpleEntry<Integer, Integer>> getFreeCells() {
        // Simple Entry is used as an alternative to Class Pair
        List<SimpleEntry<Integer, Integer>> freeCells = new ArrayList<>();

        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                if(board[i][j] == null){
                    SimpleEntry<Integer, Integer> rolCol = new SimpleEntry<>(i, j);
                    freeCells.add(rolCol);
                }
            }
        }
        return freeCells;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

}
