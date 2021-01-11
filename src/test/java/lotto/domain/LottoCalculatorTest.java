package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoCalculatorTest {
    @Test
    public void Should_ReturnTotalPrice_ForMatchResult() {
        Price totalPrice = new Price(0);
        Map<Rank, Count> rankCount = new EnumMap<>(Rank.class);
        for(Rank rank : Rank.values()) {
            rankCount.put(rank, new Count(1));
            totalPrice = totalPrice.add(rank.getPrize());
        }
        MatchResult matchResult = new MatchResult(rankCount);

        assertThat(LottoCalculator.getTotalPrice(matchResult))
                .isEqualTo(totalPrice);
    }

    @Test
    public void Should_ReturnRateOfReturn_ForInputPriceAndOutputPrice() {
        Price inputPrice = new Price(5000);
        Price outputPrice = new Price(10000);
        assertThat(LottoCalculator.getRateOfReturn(inputPrice, outputPrice))
                .isEqualTo(new Rate((10000 - 5000) * 100 /5000));
    }
}
