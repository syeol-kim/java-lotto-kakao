package lotto.domain;

public final class LottoCalculator {
    private LottoCalculator() {
    }

    public static Price getTotalPrice(MatchResult matchResult) {
        Price totalPrice = new Price();
        for (Rank rank : Rank.values()) {
            totalPrice = totalPrice.add(
                    getPrizeForRank(rank.getPrize(), matchResult.getCount(rank)));
        }
        return totalPrice;
    }

    public static Rate getRateOfReturn(Price inputPrice, Price outputPrice) {
        if (inputPrice.getPrice() == 0) {
            throw new IllegalArgumentException("투입 금액은 0원 보다 커야 합니다.");
        }
        return new Rate((int) ((outputPrice.getPrice() - inputPrice.getPrice()) * 100L / inputPrice.getPrice()));
    }

    private static Price getPrizeForRank(Price prize, Count count) {
        return new Price(prize.getPrice()
                * count.getCount());
    }
}
