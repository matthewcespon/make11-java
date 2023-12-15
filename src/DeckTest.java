public class DeckTest {
    public static void main(String[] args) {
        //Testing that cards will be removed everytime deck.deal is called
        Deck deck = new Deck();
        //Print 50 cards
        System.out.println("Dealing 50 Cards");
        for (int i=0; i<50; i++){
            System.out.println(deck.deal());
        }
        System.out.println("\n");

        //Expect two
        System.out.println("Last remaining cards");
        System.out.println(deck);
    }
}
