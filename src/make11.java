import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class make11 {

    public static String[][] highscoreTable() throws Exception {
        File file = new File("src/highscores.txt");
        Scanner fileInput = new Scanner(file);
        String highscoreData;
        int marker1 = 0, marker2;
        String name, score;

        // Create a two-dimensional array to store the high scores
        String[][] highscores = new String[5][2]; // Assuming there are a maximum of 5 high scores

        int index = 0; // Index for storing high scores in the array
        while (fileInput.hasNextLine()) {
            highscoreData = fileInput.nextLine();
            marker2 = highscoreData.indexOf(",", marker1);
            name = highscoreData.substring(marker1, marker2);
            score = highscoreData.substring(marker2 + 1);
            // Store the name and score in the array
            highscores[index][0] = name;
            highscores[index][1] = score;
            index++; // Increment the index for the next high score entry
        }
        return highscores;
    }

    public static void printScores(String[][] highscores) {
        System.out.println("Highscores:");
        for (int i = 0; i < highscores.length; i++) {
            System.out.printf("%-10s %s\n", highscores[i][0], highscores[i][1]);
        }
    }

    public static void addScores(String[][] highscores, int nscore) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        int score = nscore;
        // Create a new high score entry
        String[] newEntry = {name, String.valueOf(score)};
        // Add the new entry to the high scores array
        highscores[highscores.length - 1] = newEntry;

        // Bubble sort the high scores array
        for (int i = 0; i < highscores.length - 1; i++) {
            for (int j = 0; j < highscores.length - i - 1; j++) {
                int score1 = Integer.parseInt(highscores[j][1]);
                int score2 = Integer.parseInt(highscores[j + 1][1]);
                if (score1 < score2) {
                    // Swap the positions of the high scores
                    String[] temp = highscores[j];
                    highscores[j] = highscores[j + 1];
                    highscores[j + 1] = temp;
                }
            }
        }
        FileWriter fileW = new FileWriter("src/highscores.txt");
        for (int i = 0; i < highscores.length; i++) {
            fileW.write(highscores[i][0] + "," + highscores[i][1] + "\n");
        }
        fileW.close();
    }

    public static int getLowestScore(String[][] highscores) {
        int lowestScore = Integer.parseInt(highscores[highscores.length-1][1]);// Get index of last element in array
        return lowestScore;                                                    // Assuming it is sorted
    }

    public static void main(String[] args) throws Exception {
        printScores(highscoreTable());
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
                addScores(highscoreTable(), 5);
                printScores(highscoreTable());
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






