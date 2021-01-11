package lotto.domain;

public class LottoNumber {
    public static final int LOWER_BOUND = 1;
    public static final int UPPER_BOUND = 45;

    private final int number;

    public LottoNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (isOutOfRange(number)) {
            String message = String.format("%d -> 로또 번호는 %d~%d 사이의 정수여야 합니다.",
                    number, LOWER_BOUND, UPPER_BOUND);
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isOutOfRange(int number) {
        return number < LOWER_BOUND || number > UPPER_BOUND;
    }

    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LottoNumber) {
            LottoNumber lottoNumber = (LottoNumber) obj;
            return this.number == lottoNumber.number;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.number;
    }
}
