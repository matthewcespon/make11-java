public class CardTest {
    public static void main(String[] args) {
        //Testing core functionality of make11

        Card card = new Card();
        System.out.println(card);
        int card1 = card.getRankValue();
        //Get rank value of card
        System.out.println("Card value: "+ card1);
        card1 = Math.min(card1, 10);
        //Can rank value interact with other integers
        int neededCard = 11 - card1;
        System.out.println(card + " + Card value of " + neededCard + " makes 11!");
    }
}
