package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RankTest {
    @Test
    public void Should_ReturnSameRank_ForUniqueAttributes() {
        Rank first = Rank.valueOf(Rank.FIRST.getMatchCount(), false);
        assertThat(first)
                .isEqualTo(Rank.FIRST);

        Rank second = Rank.valueOf(Rank.SECOND.getMatchCount(), true);
        assertThat(second)
                .isEqualTo(Rank.SECOND);

        Rank third = Rank.valueOf(Rank.THIRD.getMatchCount(), false);
        assertThat(third)
                .isEqualTo(Rank.THIRD);

        Rank fourth = Rank.valueOf(Rank.FOURTH.getMatchCount(), false);
        assertThat(fourth)
                .isEqualTo(Rank.FOURTH);

        Rank fifth = Rank.valueOf(Rank.FIFTH.getMatchCount(), false);
        assertThat(fifth)
                .isEqualTo(Rank.FIFTH);

        Rank sixth = Rank.valueOf(Rank.SIXTH.getMatchCount(), false);
        assertThat(sixth)
                .isEqualTo(Rank.SIXTH);

        Rank seventh = Rank.valueOf(Rank.SEVENTH.getMatchCount(), false);
        assertThat(seventh)
                .isEqualTo(Rank.SEVENTH);

        Rank last = Rank.valueOf(Rank.LAST.getMatchCount(), false);
        assertThat(last)
                .isEqualTo(Rank.LAST);
    }
}
