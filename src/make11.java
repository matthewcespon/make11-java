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
        fileInput.close();
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
        return lowestScore;  
                                                                                // Assuming it is sorted
    }

    public static int getHighestScore(String[][] highscores) {
        int highestScore = Integer.parseInt(highscores[0][1]);// Get index of first element in array
        return highestScore;                                                 
    }

    public static Card[] dealInitialCards(Deck deck) {
        Card[] cardList = new Card[5];
        for (int j = 0; j < 5; j++){ // Deal initial 5 cards
            cardList[j] = deck.deal();
        }
        return cardList;
    }

    public static void writeRound(Card[] cardList, Card computerCard, String userCard, int round, boolean pointScored) throws Exception{
        FileWriter fileW = new FileWriter("src/replay.txt",true);
        fileW.write("Round " + round + "\n");
        fileW.write("Player's hand: " + cardList[0] + ", " + cardList[1] + ", " + cardList[2] + ", " + cardList[3] + ", " + cardList[4] + "\n");
        fileW.write("Computer's card: " + computerCard + "\n");
        fileW.write("Player's card: " + userCard + "\n");
        if(pointScored){
            fileW.write("Player scored a point\n");
            fileW.write("\n");
        } else {
            fileW.write("No point scored\n");
            fileW.write("\n");
        }
        fileW.close();
    }

    public static void clearReplay() throws Exception {
        FileWriter fileW = new FileWriter("src/replay.txt");
        fileW.write("");
        fileW.close();
    }
    
    public static void viewReplay() throws Exception {
        File file = new File("src/replay.txt");
        Scanner fileInput = new Scanner(file);
        while (fileInput.hasNextLine()) {
            System.out.println(fileInput.nextLine());
        }
        fileInput.close();
    }

    public static void main(String[] args) throws Exception {
        Deck deck = new Deck();
        printScores(highscoreTable());
        Scanner scanner = new Scanner(System.in);
        Card[] cardList = dealInitialCards(new Deck());
        Highscore highscore = new Highscore();
        RoundCount roundcount = new RoundCount();
        boolean gameOver = false;          
        boolean pointScored = false;
        while (gameOver == false && deck.deal() != null) {
            pointScored = false;
            System.out.println("\nRound " + (roundcount.getCount() + 1));
            System.out.println("\nCurrent score "+ highscore.getScore() + "\n");
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
                System.out.println("Invalid choice entered. Enter an option (A-E)");
                choice = scanner.next().toUpperCase();
            }
            int userNumber = 0;
            int userOption = 0;
            String userCard = "";
            switch (choice) {
                case "A":
                    userOption = 0;
                    userCard = cardList[0].toString();
                    userNumber = cardList[0].getRankValue();
                    break;
                case "B":
                    userOption = 1;
                    userCard = cardList[1].toString();
                    userNumber = cardList[1].getRankValue();
                    break;
                case "C":
                    userOption = 2;
                    userCard = cardList[2].toString();
                    userNumber = cardList[2].getRankValue();
                    break;
                case "D":
                    userOption = 3;
                    userCard = cardList[3].toString();
                    userNumber = cardList[3].getRankValue();
                    break;
                case "E":
                    userOption = 4;
                    userCard = cardList[4].toString();
                    userNumber = cardList[4].getRankValue();
                    break;
            }
            userNumber = Math.min(userNumber, 10);
            computerRank = Math.min(computerRank, 10);
            if (userNumber + computerRank == 11) {
                System.out.println("You win!");
                pointScored = true;
                highscore.increment();  // Increment high score
                roundcount.increment(); // Increment round count
                String faceCards = ""; // String to store index of face cards
                for (int k = 0; k < 5; k++) {
                    if (cardList[k].toString().matches(".*[JQK].*")) { // If card is a face card
                        if (cardList[userOption] != cardList[k]){ // If card is not the card the user chose, 
                                                                 // prevents the initial card from being flagged if it is a face card
                            faceCards += k; // Add index of face card to string
                        }
                    }
                } 
                if(faceCards.isEmpty() == false){
                    System.out.println("Face card detected, do you want to swap it out? (Y/N)");
                    String swapChoice = scanner.next().toUpperCase();;
                    if (swapChoice.equals("Y")) {
                        System.out.println("Which card would you like to swap out? (A-E)");
                        String swapCard = scanner.next().toUpperCase();
                        while (!swapCard.matches("[A-E]")) {
                            System.out.println("Invalid choice entered. Enter an option (A-E)");
                            swapCard = scanner.next().toUpperCase();
                        }
                        switch (swapCard) {
                            case "A":
                                if (faceCards.contains("0")) {
                                    userCard = userCard + ", " + cardList[0].toString();
                                    writeRound(cardList, computerCard, userCard, roundcount.getCount(), pointScored);
                                    cardList[0] = deck.deal();
                                } else {
                                    System.out.println("Invalid option, game continues...");
                                }
                                break;
                            case "B":
                                if (faceCards.contains("1")) {
                                    userCard = userCard + ", " + cardList[1].toString();
                                    writeRound(cardList, computerCard, userCard, roundcount.getCount(), pointScored);
                                    cardList[1] = deck.deal();
                                } else {
                                    System.out.println("Invalid option, game continues...");
                                }
                                break;
                            case "C":
                                if (faceCards.contains("2")) {
                                    //write cardlist2.suit to fi
                                    userCard = userCard + ", " + cardList[2].toString();
                                    writeRound(cardList, computerCard, userCard, roundcount.getCount(), pointScored);
                                    cardList[2] = deck.deal();
                                } else {
                                    System.out.println("Invalid option, game continues...");
                                }
                                break;
                            case "D":
                                if (faceCards.contains("3")) {
                                    userCard = userCard + ", " + cardList[3].toString();
                                    writeRound(cardList, computerCard, userCard, roundcount.getCount(), pointScored);
                                    cardList[3] = deck.deal();
                                } else {
                                    System.out.println("Invalid option, game continues...");
                                }
                                break;
                            case "E":
                                if (faceCards.contains("4")) {
                                    userCard = userCard + ", " + cardList[4].toString();
                                    writeRound(cardList, computerCard, userCard, roundcount.getCount(), pointScored);
                                    cardList[4] = deck.deal();
                                } else {
                                    System.out.println("Invalid option, game continues...");
                                }
                                break; 
                        }
                    }
                }
                cardList[userOption] = deck.deal();
            } else if (cardList[userOption].getSuit() == computerCard.getSuit()){
                System.out.println("Matching suit! no points awarded");
                pointScored = false;
                roundcount.increment();
                writeRound(cardList, computerCard, userCard, roundcount.getCount(), pointScored);
                cardList[userOption] = deck.deal();
            } else if (deck.deckIsEmpty()) {
                System.out.println("Deck is empty game over! :(");
                printScores(highscoreTable());
                break;
            }
            else {
                System.out.println("You lose!");
                System.out.println("Your highscore is " + highscore.getScore() + ".");
                gameOver = true;
                printScores(highscoreTable());
                pointScored = false;
                roundcount.increment();
                writeRound(cardList, computerCard, userCard, roundcount.getCount(), pointScored);
            } 
        }
        if (highscore.getScore() >= getLowestScore(highscoreTable())) {
            System.out.println("\nYou made the highscore table!");
            addScores(highscoreTable(), highscore.getScore());
            System.out.println("UPDATED SCORES\n");
            printScores(highscoreTable());
        }
        System.out.println("\nWould you like to view the replay? (Y/N)");
        String replayChoice = scanner.next().toUpperCase();
        while(!replayChoice.matches("[YN]")){
            System.out.println("Invalid choice entered. Enter an option (Y/N)");
            replayChoice = scanner.next().toUpperCase();
        }
        if(replayChoice.equals("Y")){
            viewReplay();
        }
        clearReplay();
    }
}

