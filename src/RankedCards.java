public class RankedCards {
    public static void main(String[] args) {
        Card myCards[]= new Card[5];
        int biggestValue = -1, biggestPos = -1;
        Card tempCard;
        for (int i = 0;i<5;i++){
            myCards[i] = new Card();
            if (myCards[i].getRankValue() > biggestValue){
                biggestValue = myCards[i].getRankValue();
                biggestPos = i;
            }
        }
        System.out.println("My cards are...");
        for (Card card:myCards){
            System.out.println(card);
        }
        System.out.println("\nThe biggest card is the "+ biggestPos);
        tempCard = myCards[4];
        myCards[4] = myCards[biggestPos];
        myCards[biggestPos] = tempCard;
        System.out.println("\nAfter swapping...");
        for (Card card:myCards){
            System.out.println(card);
        }
    }
}
