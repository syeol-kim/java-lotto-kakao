package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoMatcher {
    private final WinningNumbers winningNumbers;

    public LottoMatcher(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public MatchResult match(LottoTicket ticket) {
        int matchCount = calcMatchCount(ticket);
        boolean matchBonus = isMatchBonus(ticket);
        return MatchResult.valueOf(matchCount, matchBonus);
    }

    public MatchResults match(LottoTickets lottoTickets) {
        List<MatchResult> results = lottoTickets.toStream()
                .map(this::match)
                .collect(Collectors.toList());
        return new MatchResults(results);
    }

    private int calcMatchCount(LottoTicket lottoTicket) {
        LottoNumbers luckyNumbers = winningNumbers.getLuckyNumbers();

        return (int) luckyNumbers.toStream()
                .filter(lottoTicket::contains)
                .count();
    }

    private boolean isMatchBonus(LottoTicket lottoTicket) {
        LottoNumber bonusNumber = winningNumbers.getBunusNumber();
        return lottoTicket.contains(bonusNumber);
    }
}
