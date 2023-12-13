import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class make11Test {

    @Test
    //TEST DEALINITIALCARDS() RETURNS ARRAY OF 5
    void testdealInitialCards() {
        Deck deck = new Deck();
        Card[] actualCards = make11.dealInitialCards(new Deck());
        Assertions.assertEquals(5, actualCards.length);
    }


//    ronald,8
//    johnson,7
//    jack,7
//    john,4
//    jen,3


    @Test
        //TEST ADDSCORES() ADDS THE SCORES TO THE FILE
    void testaddScores() throws Exception {
        make11 make11 = new make11();
        make11.addScores(make11.highscoreTable(),9,"HIGHSCORE");
        assertEquals(9,make11.getHighestScore(make11.highscoreTable()));
    }

    @Test
        //TEST GETLOWESTSCORE() GETS LOWEST SCORE FROM HIGHSCORES.TXT
    void testgetLowestScore() throws Exception {
        assertEquals(4,make11.getLowestScore(make11.highscoreTable()));
    }


}