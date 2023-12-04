import java.util.ArrayList;
import java.util.Scanner;

public class CardTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        Card[] cardList = new Card[5];
        for (int j = 0; j < 5; j++){ // Deal initial 5 cards
            cardList[j] = deck.deal();
        }
        int roundCount = 0;
        int highScore = 0;
        int gameOver = 0;        
        while (gameOver == 0 && deck.deal() != null) {
            System.out.println("\nRound " + (roundCount + 1) + "\n");
            Card computerCard = deck.deal();
            int computerRank = computerCard.getRankValue();
            System.out.println("The computer's card is the " + computerCard + "\n");
            int i=0;
            for (char letter = 'A'; letter <= 'E'; letter++) { // Display cards A-E
                System.out.println(letter + " " + cardList[i].toString());
                i++;
            }
            System.out.println("\nWhich card do you want to play? (A-E)");
            String choice = scanner.next().toUpperCase();

            while (!choice.matches("[A-E]")) {
                System.out.println("Invalid choice. Please enter an option (A-E)");
                choice = scanner.next().toUpperCase();
            }
            int userNumber = 0;
            int userOption = 0;
            switch (choice) {
                case "A":
                    userNumber = cardList[0].getRankValue();
                    userOption = 0;
                    break;
                case "B":
                    userNumber = cardList[1].getRankValue();
                    userOption = 1;
                    break;
                case "C":
                    userNumber = cardList[2].getRankValue();
                    userOption = 2;
                    break;
                case "D":
                    userNumber = cardList[3].getRankValue();
                    userOption = 3;
                    break;
                case "E":
                    userNumber = cardList[4].getRankValue();
                    userOption = 4;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter A-E.");
                    break;
            }
            
            userNumber = Math.min(userNumber, 10);
            computerRank = Math.min(computerRank, 10);
            System.out.println();
            System.out.println();
            if (userNumber + computerRank == 11) {
                System.out.println("You win!");
                highScore++;
                roundCount++;
                cardList[userOption] = deck.deal();
            } else if (cardList[userOption].getSuit() == computerCard.getSuit()){
                System.out.println("Matching suit! no points awarded");
                roundCount++;
                cardList[userOption] = deck.deal();
            } else {
                System.out.println("You lose!");
                System.out.println("Your highscore is " + highScore + ".");
                gameOver = 1;
                break;
            } if (deck.deckIsEmpty()) {
                System.out.println("Deck is empty");
                break;
            }
        }
        System.out.println("outside of for loop");
        //ASK FOR REPLAY
    }
}






