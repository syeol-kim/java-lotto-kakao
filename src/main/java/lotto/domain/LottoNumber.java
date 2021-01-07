package lotto.domain;

public class LottoNumber {
    static final int LOWER_BOUND = 1;
    static final int UPPER_BOUND = 45;

    private final int number;

    public LottoNumber(int number) {
        if (!isInRange(number)) {
            String message = String.format("Number should be between %d~%d.",
                    LOWER_BOUND,
                    UPPER_BOUND);
            throw new IllegalArgumentException(message);
        }

        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    private boolean isInRange(int number) {
        return LOWER_BOUND <= number && number <= UPPER_BOUND;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LottoNumber) {
            LottoNumber lottoNumber = (LottoNumber) obj;
            return this.number == lottoNumber.getNumber();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.number;
    }
}
