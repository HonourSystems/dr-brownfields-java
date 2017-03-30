package drBrownFields;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    public Deck() {
        initialize();
    }

    public void shuffle() {
        Random r = new Random();
        for (int i = 0; i < 100; ++i) {
            swap(r.nextInt(52), r.nextInt(52));
        }
    }

    public Card deal() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }

    private void initialize() {
        cards.clear();
        for (Suit suit = Suit.Clubs; suit.ordinal() < Suit.Spades.ordinal(); suit = Suit.values()[suit.ordinal() + 1]) {
            for (Rank rank = Rank.Two; rank.ordinal() < Rank.Ace.ordinal(); rank = Rank.values()[rank.ordinal() + 1]) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    private void swap(int lowerIndex, int upperIndex) {
        int cardCount = cards.size();
        if (lowerIndex != upperIndex && lowerIndex >= 0 && upperIndex >= 0 && lowerIndex <= --cardCount && upperIndex <= --cardCount) {
            Card temp = cards.get(lowerIndex);
            cards.set(lowerIndex, cards.get(upperIndex));
            cards.set(upperIndex, temp);
        }
    }

    private List<Card> cards = new ArrayList<Card>();

}
