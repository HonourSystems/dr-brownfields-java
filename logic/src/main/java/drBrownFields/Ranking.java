package drBrownFields;

public enum Ranking {
    Nothing(0),
    Pair(5),
    TwoPair(10),
    ThreeOfAKind(15),
    Straight(20),
    Flush(25),
    FullHouse(40),
    FourOfAKind(125),
    StraightFlush(250),
    RoyalFlush(2000);

    private final int value;

    Ranking(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }
}
