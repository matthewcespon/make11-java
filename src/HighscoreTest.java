import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighscoreTest {
    Highscore highscore = new Highscore();
    @Test
    void testInitialValue() {
        assertEquals(0,highscore.getScore());
    }

    @Test
    void testScoreAfterIncrement() {
        highscore.increment();
        assertEquals(1,highscore.getScore());
    }

    @Test
    void testMultipleIncrements() {
        for(int i = 0; i < 5;i++){
            highscore.increment();
        }
        assertEquals(5,highscore.getScore());
    }
}