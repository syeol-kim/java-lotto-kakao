package lotto.domain;

public class StatisticsResult {
    private final MatchResults matchResults;
    private final Rate earningRate;

    public StatisticsResult(MatchResults matchResults, Rate earningRate){
        this.matchResults = matchResults;
        this.earningRate = earningRate;
    }

    public MatchResults getMatchResults() {
        return matchResults;
    }

    public Rate getEarningRate() {
        return earningRate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StatisticsResult)) {
            return false;
        }

        StatisticsResult result = (StatisticsResult) obj;
        if (!earningRate.equals(result.earningRate)) {
            return false;
        }

        return matchResults.equals(result.matchResults);
    }
}
