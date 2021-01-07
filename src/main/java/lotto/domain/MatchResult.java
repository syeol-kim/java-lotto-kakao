package lotto.domain;

public enum MatchResult {
    FIRST(6, 2000000000, "1등"),
    SECOND(5, 30000000, "2등(보너스 볼 일치)"),
    THIRD(5, 1500000, "3등"),
    FOURTH(4, 50000, "4등"),
    FIFTH(3, 5000, "5등"),
    NULL(0, 0, "꽝");

    private int matchCount;
    private int reward;
    private String rank;

    MatchResult(int matchCount, int reward, String rank) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.rank = rank;
    }

    public static MatchResult valueOf(int matchCount, boolean matchBonus) {
        if (matchCount == 6) {
            return MatchResult.FIRST;
        }

        if (matchCount == 5 && matchBonus) {
            return MatchResult.SECOND;
        }

        if (matchCount == 5) {
            return MatchResult.THIRD;
        }

        if (matchCount == 4) {
            return MatchResult.FOURTH;
        }

        if (matchCount == 3) {
            return MatchResult.FIFTH;
        }

        return MatchResult.NULL;
    }

    public int getReward(){
        return this.reward;
    }

    public int getMatchCount(){
        return this.matchCount;
    }

    public String getRank() {
        return this.rank;
    }
}
