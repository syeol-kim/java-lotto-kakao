package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMatchingCheckerTest {
    @Test
    public void Should_ReturnMatchResult_ForWinningNumbersAndLottoTicket() {
        LottoNumbers luckyNumbers = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()));
        LottoNumber bonusNumber = new LottoNumber(7);

        WinningNumbers winningNumbers = new WinningNumbers(luckyNumbers, bonusNumber);
        LottoTicket ticket = new LottoTicket(
                Arrays.asList(
                        new LottoNumbers(   // RANK.SECOND
                                Stream.of(2, 3, 4, 5, 6, 7)
                                .map(LottoNumber::new)
                                .collect(Collectors.toList())
                        ),
                        new LottoNumbers(   // RANK.FIFTH
                                Stream.of(4, 5, 6, 7, 41, 42)
                                        .map(LottoNumber::new)
                                        .collect(Collectors.toList())),
                        new LottoNumbers(   // RANK.FIFTH
                                Stream.of(1, 2, 3, 41, 42, 43)
                                        .map(LottoNumber::new)
                                        .collect(Collectors.toList()))
                )
        );

        LottoMatchingChecker matchingChecker = new LottoMatchingChecker();
        MatchResult matchResult = matchingChecker.getMatchResult(winningNumbers, ticket);

        assertThat(matchResult.getCount(Rank.SECOND))
                .isEqualTo(new Count(1));

        assertThat(matchResult.getCount(Rank.THIRD))
                .isEqualTo(new Count(0));

        assertThat(matchResult.getCount(Rank.FIFTH))
                .isEqualTo(new Count(2));
    }
}
