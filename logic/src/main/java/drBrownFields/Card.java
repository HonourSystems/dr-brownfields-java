package drBrownFields;
public class Card implements Comparable<Card> {
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank rank;

    public Suit suit;


    @Override
    public int compareTo(Card other) {
        if (this.rank.ordinal() < other.getRank().ordinal()) {
            return -1;
        } else if (this.rank.ordinal() == other.getRank().ordinal()) {
            return 0;
        } else {
            return 1;
        }
    }
}

