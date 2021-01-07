package lotto.domain;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MatchResultsTest {
    @Test
    public void matchesAreCorrect() {
        LottoNumbers luckyNumbers = new LottoNumbers(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(luckyNumbers, bonusNumber);
        LottoMatcher lottoMatcher = new LottoMatcher(winningNumbers);

        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(
                new LottoTicket(customLottoNumbers(1, 2, 3, 4, 5, 6)),
                new LottoTicket(customLottoNumbers(1, 2, 3, 4, 5, 7)),
                new LottoTicket(customLottoNumbers(1, 2, 3, 4, 5, 8)),
                new LottoTicket(customLottoNumbers(1, 2, 3, 4, 8, 9)),
                new LottoTicket(customLottoNumbers(1, 2, 3, 8, 9, 10)),
                new LottoTicket(customLottoNumbers(1, 2, 3, 7, 8, 9)),
                new LottoTicket(customLottoNumbers(1, 2, 7, 8, 9, 10)),
                new LottoTicket(customLottoNumbers(1, 2, 8, 9, 10, 11))));

        assertThat(lottoMatcher.match(lottoTickets))
                .isEqualTo(new MatchResults(Arrays.asList(
                        MatchResult.FIRST,
                        MatchResult.SECOND,
                        MatchResult.THIRD,
                        MatchResult.FOURTH,
                        MatchResult.FIFTH,
                        MatchResult.FIFTH,
                        MatchResult.NULL,
                        MatchResult.NULL
                )));
    }

    private LottoNumbers customLottoNumbers(int... parameters) {
        return new LottoNumbers(Stream.of(parameters[0], parameters[1], parameters[2],
                parameters[3], parameters[4], parameters[5])
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
