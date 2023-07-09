import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    int diceCount = 0; // dice count means, number of dice used in the game
    int min = 1;
    int max = 6;

    public Dice(int diceCount){
        this.diceCount = diceCount;
    }

    public int rollDice(){
        int totalSum = 0;
        int diceUsed = 0;

        // this loop is to run number of dice available
        // ThreadLocalRandom generates the random number between min to max
        while(diceUsed < diceCount){
            totalSum += ThreadLocalRandom.current().nextInt(min, max+1);
            diceUsed++;
        }

        return totalSum;
    }
}
