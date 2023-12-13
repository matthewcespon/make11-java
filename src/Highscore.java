
public class Highscore {
    private int score;

    public Highscore() {
        score = 0;
    }

    public void increment() {
        score++;
    }
    
    public int getScore() {
        return score;
    }
}