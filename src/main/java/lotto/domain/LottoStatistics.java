package lotto.domain;

import java.util.NoSuchElementException;

public class LottoStatistics {
    private final MatchResults results;
    private final InsertPrice insertPrice;
    private final TotalPrice totalPrice;

    public LottoStatistics(MatchResults results, InsertPrice insertPrice) {
        this.results = results;
        this.insertPrice = insertPrice;
        this.totalPrice = getTotalPrice(results);
    }

    public StatisticsResult getStatisticsResult() {
        double rateInRealNumber = (double) totalPrice.getTotalPrice() / insertPrice.getPrice();
        Rate rate = new Rate((int) Math.round(rateInRealNumber * 100));
        return new StatisticsResult(results, rate);
    }

    private TotalPrice getTotalPrice(MatchResults results) {
        return new TotalPrice(results.getResultCounts()
                .entrySet()
                .stream()
                .map(entry -> {
                    MatchResult result = entry.getKey();
                    int count = entry.getValue();

                    return (long) result.getReward() * count;
                })
                .reduce(Long::sum)
                .orElseThrow(NoSuchElementException::new));
    }
}
