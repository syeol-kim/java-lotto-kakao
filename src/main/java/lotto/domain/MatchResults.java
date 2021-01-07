package lotto.domain;

import java.util.*;

public class MatchResults {
    private Map<MatchResult, Integer> resultCounter;

    public MatchResults(List<MatchResult> results) {
        resultCounter = new EnumMap<>(MatchResult.class);
        for (MatchResult result : results) {
            insertResult(result);
        }
    }

    public Map<MatchResult, Integer> getResultCounts() {
        return resultCounter;
    }

    private void insertResult(MatchResult result) {
        int count = Optional.ofNullable(resultCounter.get(result))
                .orElse(0);

        resultCounter.put(result, count + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchResults that = (MatchResults) o;
        return Objects.equals(resultCounter, that.resultCounter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCounter);
    }
}
