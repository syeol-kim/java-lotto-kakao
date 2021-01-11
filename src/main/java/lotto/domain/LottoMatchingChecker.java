package lotto.domain;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public final class LottoMatchingChecker {
    private final Map<Rank, Count> rankCounter;

    public LottoMatchingChecker() {
        rankCounter = new EnumMap<>(Rank.class);
    }

    public MatchResult getMatchResult(WinningNumbers winningNumbers, LottoTicket ticket) {
        for (LottoNumbers lottoNumbers : ticket.getLottoNumbersList()) {
            Count matchCount = winningNumbers.getNumberOfElementsInLuckyNumbers(lottoNumbers);
            boolean matchBonus = winningNumbers.matchBonusNumber(lottoNumbers);
            Rank rank = Rank.valueOf(matchCount, matchBonus);
            increaseCount(rank);
        }

        return new MatchResult(rankCounter);
    }

    private void increaseCount(Rank rank) {
        Count count = getCount(rank);
        count.increase();
        rankCounter.put(rank, count);
    }

    private Count getCount(Rank rank) {
        return Optional.ofNullable(
                rankCounter.get(rank))
                .orElse(new Count(0));
    }
}
