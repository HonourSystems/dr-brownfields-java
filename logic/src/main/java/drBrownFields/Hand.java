package drBrownFields;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    public void addCard(Card card) {
        cards.add(card);
        calculatebestRanking();
    }

    public void exchangeCard(int oldCardIndex, Card newCard) {
        if (oldCardIndex >= 0 || oldCardIndex <= cards.size()) {
            Card card = cards.get(oldCardIndex);
            cards.remove(oldCardIndex);
            cards.add(oldCardIndex, newCard);
            calculatebestRanking();
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Ranking getbestRanking() {
        return bestRanking;
    }

    public Ranking bestRanking;

    private void calculatebestRanking() {
        if (cards.size() < 5) {
            return;
        }
        Collections.sort(cards);
        List<Card> sc = cards;
        if (isFlush(sc) && isStraight(sc)) {
            if (sc.get(4).getRank() == Rank.Ace) {
                bestRanking = Ranking.RoyalFlush;
            } else {
                bestRanking = Ranking.StraightFlush;
            }
        } else if (isThreeOfAKind(sc, 1, 2, 3) && (isPair(sc, 0, 1) || isPair(sc, 3, 4))) {
            bestRanking = Ranking.FourOfAKind;
        } else if (isPair(sc, 0, 1) && isThreeOfAKind(sc, 2, 3, 4) || isPair(sc, 3, 4) && isThreeOfAKind(sc, 0, 1, 2)) {
            bestRanking = Ranking.FullHouse;
        } else if (isFlush(sc)) {
            bestRanking = Ranking.Flush;
        } else if (isStraight(sc)) {
            bestRanking = Ranking.Straight;
        } else if (isThreeOfAKind(sc, 0, 1, 2) || isThreeOfAKind(sc, 1, 2, 3) || isThreeOfAKind(sc, 2, 3, 4)) {
            bestRanking = Ranking.ThreeOfAKind;
        } else if (isPair(sc, 0, 1) && isPair(sc, 2, 3) || isPair(sc, 0, 1) && isPair(sc, 3, 4) || isPair(sc, 1, 2) && isPair(sc, 3, 4)) {
            bestRanking = Ranking.TwoPair;
        } else if (isPair(sc, 0, 1) || isPair(sc, 1, 2) || isPair(sc, 2, 3) || isPair(sc, 3, 4)) {
            bestRanking = Ranking.Pair;
        } else {
            bestRanking = Ranking.Nothing;
        }
    }

    private static boolean isThreeOfAKind(List<Card> c, int index1, int index2, int index3) {
        return isPair(c, index1, index2) && isPair(c, index2, index3);
    }

    private static boolean isPair(List<Card> c, int index1, int index2) {
        return c.get(index1).getRank() == c.get(index2).getRank();
    }

    private static boolean isFlush(List<Card> c) {
        return c.get(0).getSuit() == c.get(1).getSuit() && c.get(1).getSuit() == c.get(2).getSuit() && c.get(2).getSuit() == c.get(3).getSuit() && c.get(3).getSuit() == c.get(4).getSuit();
    }

    private static boolean isStraight(List<Card> c) {
        return c.get(0).getRank().ordinal() == c.get(1).getRank().ordinal() - 1 && c.get(1).getRank().ordinal() == c.get(2).getRank().ordinal() - 1 && c.get(2).getRank().ordinal() == c.get(3).getRank().ordinal() - 1 && c.get(3).getRank().ordinal() == c.get(4).getRank().ordinal() - 1;
    }

    private List<Card> cards = new ArrayList<Card>();
}

