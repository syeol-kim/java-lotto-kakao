package lotto.domain;

import java.util.Map;
import java.util.Optional;

public class MatchResult {
    private final Map<Rank, Count> rankCounter;

    public MatchResult(Map<Rank, Count> rankCounter) {
        this.rankCounter = rankCounter;
    }

    public Count getCount(Rank rank) {
        return Optional.ofNullable(
                rankCounter.get(rank))
                .orElse(new Count(0));
    }
}
