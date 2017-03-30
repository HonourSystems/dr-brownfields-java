package drBrownFields;

public class Game {

    public Game() {
        stake = 100;
        bet = 5;
        win = 0;
    }

    public int getStake() {
        return stake;
    }

    public int getBet() {
        return bet;
    }

    public int getWin() {
        return win;
    }

    public Hand getHand() {
        return hand;
    }

    public int stake;

    public int bet;

    public int win;

    public Hand hand;

    private Deck deck;

    public void deal() {
        deck = new Deck();
        hand = new Hand();
        deck.shuffle();
        for (int i = 0; i < 5; ++i) {
            hand.addCard(deck.deal());
        }
        stake -= bet;
        win = (int) hand.getbestRanking().getValue();
    }

    public void draw(int[] cardIndexes) {
        for (int index : cardIndexes) {
            hand.exchangeCard(index, deck.deal());
        }
        win = (int) hand.getbestRanking().getValue();
        stake += win;
    }

}
