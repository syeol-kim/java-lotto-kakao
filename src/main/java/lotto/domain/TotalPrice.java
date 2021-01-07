package lotto.domain;

public class TotalPrice {
    private final long price;

    public TotalPrice(long price) {
        if (isNegative(price)) {
            String message = "Total price cannot be negative";
            throw new IllegalArgumentException(message);
        }

        this.price = price;
    }

    public long getTotalPrice() {
        return price;
    }

    private boolean isNegative(long price) {
        return price < 0;
    }
}
