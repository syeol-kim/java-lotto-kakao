package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchResultTest {
    @Test
    public void Should_SucceedInCreatingMatchResult() {
        MatchResult matchResult = new MatchResult(new EnumMap<>(Rank.class));
        assertThat(matchResult)
                .isInstanceOf(MatchResult.class);
    }

    @Test
    public void Should_ReturnMatchingCount_ForRank() {
        Map<Rank, Count> rankCounter = new EnumMap<>(Rank.class);
        rankCounter.put(Rank.FIRST, new Count(1));
        rankCounter.put(Rank.FIFTH, new Count(3));
        rankCounter.put(Rank.SIXTH, new Count(2));

        MatchResult matchResult = new MatchResult(rankCounter);
        assertThat(matchResult.getCount(Rank.FIRST))
                .isEqualTo(new Count(1));
        assertThat(matchResult.getCount(Rank.FIFTH))
                .isEqualTo(new Count(3));
        assertThat(matchResult.getCount(Rank.SIXTH))
                .isEqualTo(new Count(2));
        assertThat(matchResult.getCount(Rank.SECOND))
                .isEqualTo(new Count(0));
    }
}
