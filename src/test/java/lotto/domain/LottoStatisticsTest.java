package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoStatisticsTest {
    private LottoNumbers luckyNumbers;
    private LottoNumber bonusNumber;
    private WinningNumbers winningNumbers;
    private LottoMatcher matcher;
    private InsertPrice insertPrice;

    @BeforeEach
    public void setUp() {
        luckyNumbers = new LottoNumbers(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        bonusNumber = new LottoNumber(7);
        winningNumbers = new WinningNumbers(luckyNumbers, bonusNumber);
        matcher = new LottoMatcher(winningNumbers);
        insertPrice = new InsertPrice(3000);
    }

    @Test
    public void firstSecondFifthMatchStatistics() {
        LottoTicket firstPlace = new LottoTicket(customLottoNumbers(1, 2, 3, 4, 5, 6));
        LottoTicket secondPlace = new LottoTicket(customLottoNumbers(1, 2, 3, 4, 5, 7));
        LottoTicket fifthPlace = new LottoTicket(customLottoNumbers(1, 2, 3, 8, 9, 10));
        LottoTickets tickets = new LottoTickets(
                Arrays.asList(
                        firstPlace,
                        secondPlace,
                        fifthPlace
                ));

        LottoStatistics statistics = new LottoStatistics(matcher.match(tickets), insertPrice);
        StatisticsResult statisticsResult = statistics.getStatisticsResult();

        double expectedRateInRealNumber = (double) ((long) MatchResult.FIRST.getReward()
                + MatchResult.SECOND.getReward() + MatchResult.FIFTH.getReward()) / insertPrice.getPrice();
        MatchResults expectedMatches = new MatchResults(Arrays.asList(
                MatchResult.FIRST,
                MatchResult.SECOND,
                MatchResult.FIFTH
        ));
        Rate expectedRate = new Rate((int) (expectedRateInRealNumber ));
        assertThat(statisticsResult)
                .isEqualTo(new StatisticsResult(expectedMatches, expectedRate));
    }

    private LottoNumbers customLottoNumbers(int... parameters) {
        return new LottoNumbers(Stream.of(parameters[0], parameters[1], parameters[2],
                parameters[3], parameters[4], parameters[5])
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }
}
