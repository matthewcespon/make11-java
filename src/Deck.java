import java.util.ArrayList;
import java.util.Collections;
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    public Deck(){
        for (int i=0;i<52;i++){
            this.deck.add(new Card(i % 13, i/13));
        }
        Collections.shuffle(this.deck);
    }

    public Card deal(){
        if (this.deck.size()>0){
            Card card = this.deck.get(0);
            this.deck.remove(0);
            return card;
        } else return null;
    }

    public boolean deckIsEmpty(){
        return (deal()==null);
    }

    public String toString(){
        String result = "\n";
        for (Card card : deck){
            result += card + "\n";
        }
        return result;
    }
}
