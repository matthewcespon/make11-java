public class CardTest {
    public static void main(String[] args) {
        //Testing core functionality of make11

        Card card1 = new Card();
        System.out.println(card1);
        //Get rank value of card
        System.out.println("Card value: "+ card1.getRankValue());
        //Can rank value interact with other integers
        int neededCard = 11 - card1.getRankValue();
        System.out.println(card1 + " + Card value of " + neededCard + " makes 11!");
    }
}
