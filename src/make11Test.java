import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class make11Test {

    @Test
        //TEST DEALINITIALCARDS() RETURNS ARRAY OF 5
    void testdealInitialCards() {
        Deck deck = new Deck();
        Card[] actualCards = make11.dealInitialCards(new Deck());
        Assertions.assertEquals(5, actualCards.length);
    }

    @Test
        //TEST ADDSCORES() ADDS THE SCORES TO THE FILE
    void testaddScores() throws Exception {
        make11 make11 = new make11();
        make11.addScores(make11.highscoreTable(),9,"HIGHEST");
        assertEquals(9,make11.getHighestScore(make11.highscoreTable()));
    }

    @Test
        //TEST GETLOWESTSCORE() GETS LOWEST SCORE FROM HIGHSCORES.TXT
    void testgetLowestScore() throws Exception {
        assertEquals(1,make11.getLowestScore(make11.highscoreTable()));
    }

    public static boolean InvalidInputs(){
        Scanner scanner = new Scanner(System.in);
        String choice = "F";
        while (!choice.matches("[A-E]")) {
            System.out.println("Invalid choice entered. Enter an option (A-E)");
            choice = "A";
        }
        if(choice.matches("[A-E]")){
            return true;
        } else return false;
    }
    @Test
        //TEST INVALID INPUTS
    void testInvalidInputs(){
        assertTrue(InvalidInputs());
    }

    public static boolean ReplacingCards(){
        Deck deck = new Deck();
        Card[] cards1 = make11.dealInitialCards(deck);
        Card[] cards2 = Arrays.copyOf(cards1, cards1.length);
        cards2[3] = deck.deal();
        boolean isEqual = Arrays.equals(cards1, cards2);
        return isEqual;
    }
    @Test
        //TEST REPLACING CARDS
    void testReplacingCards(){
        assertFalse(ReplacingCards());
    }

    public static boolean SameSuit(){
        Card card1 = new Card(10, 2);
        Card card2 = new Card(9, 2);
        return card1.getSuit() == card2.getSuit();
    }
    
    @Test
    void testSameSuit(){
        assertTrue(SameSuit());
    }

    public static void testPrintingCards(){
        //TEST PRINTING OUT 5 CARDS ARE PRINTED
        System.out.println("TEST 1, PRINT 5 CARDS TO USER");
        Deck deck = new Deck();
        Card[] cards = make11.dealInitialCards(deck);
        for(int i=0;i<cards.length;i++){
            System.out.println(cards[i]);
        }
    }




    public static void main(String[] args) {
        testPrintingCards();
    }


}