package lotto.domain;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    SIXTH(2, 0),
    SEVENTH(1, 0),
    LAST(0, 0);

    private final Count matchCount;
    private final Price prize;

    Rank(Count matchCount, Price prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    Rank(int matchCount, int prize) {
        this(new Count(matchCount), new Price(prize));
    }

    public static Rank valueOf(Count matchCount, boolean matchBonus) {
        if (matchCount.equals(FIRST.matchCount)) {
            return FIRST;
        }

        if (matchCount.equals(SECOND.matchCount) && matchBonus) {
            return SECOND;
        }

        if (matchCount.equals(THIRD.matchCount)) {
            return THIRD;
        }

        if (matchCount.equals(FOURTH.matchCount)) {
            return FOURTH;
        }

        if (matchCount.equals(FIFTH.matchCount)) {
            return FIFTH;
        }

        if (matchCount.equals(SIXTH.matchCount)) {
            return SIXTH;
        }

        if (matchCount.equals(SEVENTH.matchCount)) {
            return SEVENTH;
        }

        return LAST;
    }

    public Price getPrize() {
        return prize;
    }

    public Count getMatchCount() {
        return matchCount;
    }
}
