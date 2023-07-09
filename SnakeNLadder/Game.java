import java.util.Deque;
import java.util.LinkedList;

public class Game {

    Board board;
    Dice dice;

    // deque is taken because if we have two players then after player1 turn we will remove player1 and then push it again
    // after player 2, and after player2 turn we weill remove p2 and push it after p1
    Deque<Player> playerList = new LinkedList<>();
    Player winner;

    public Game(){
        initializeGame();
    }

    private  void initializeGame(){
        board = new Board(10, 5, 4);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers(){
        Player player1 = new Player("p1", 0);
        Player player2 = new Player("p2", 0);
        playerList.add(player1);
        playerList.add(player2);
    }

    public void startGame() {
        while(winner == null){

            // check whose turn
            Player playerTurn = findPlayerTurn();
            System.out.println("player turn is:" + playerTurn.id + " current position is: " + playerTurn.currentPosition);

            //roll the dice
            int diceNumbers = dice.rollDice();

            // get the new position
            int playerNewPosition = playerTurn.currentPosition + diceNumbers;
            playerNewPosition = jumpCheck(playerNewPosition); // to check if we can go on that position -> if snake go down, if ladder go up
            playerTurn.currentPosition = playerNewPosition;

            System.out.println("player turn is:" + playerTurn.id + " new Position is: " + playerNewPosition);

            // check for win condition
            if(playerNewPosition >= board.cells.length*board.cells.length-1){
                winner = playerTurn;
            }
        }

        System.out.println("Winner is "+ winner.id);

    }

    private Player findPlayerTurn(){
        Player playerTurn = playerList.removeFirst();
        playerList.add(playerTurn);
        return playerTurn;
    }

    private int jumpCheck(int playerNewPosition) {
        if(playerNewPosition > board.cells.length*board.cells.length-1) {
            return playerNewPosition;
        }

        Cell cell = board.getCell(playerNewPosition);
        if(cell.jump != null && cell.jump.start == playerNewPosition) {
            // ladder or snake is available

            String jumpBy = (cell.jump.start < cell.jump.end) ? "ladder" : "snake";
            System.out.println("jump done by: " + jumpBy);
            return cell.jump.end;
        }

        return playerNewPosition;

    }


}
