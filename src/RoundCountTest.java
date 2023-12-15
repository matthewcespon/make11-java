import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundCountTest {
    RoundCount roundcount = new RoundCount();
    @Test
    void testInitialValue() {
        assertEquals(0,roundcount.getCount());
    }

    @Test
    void testScoreAfterIncrement() {
        roundcount.increment();
        assertEquals(1,roundcount.getCount());
    }

    @Test
    void testMultipleIncrements() {
        for(int i = 0; i < 5;i++){
            roundcount.increment();
        }
        assertEquals(5,roundcount.getCount());
    }
}