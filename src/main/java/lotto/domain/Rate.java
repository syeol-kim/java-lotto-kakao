package lotto.domain;

public class Rate {
    private final int rate;

    public Rate(int rate) {
        if (isNegative(rate)) {
            String message = "rate cannot be negative";
            throw new IllegalArgumentException(message);
        }
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    private boolean isNegative(int rate) {
        return rate < 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rate) {
            Rate rate = (Rate) obj;
            return this.rate == rate.rate;
        }
        return false;
    }
}
