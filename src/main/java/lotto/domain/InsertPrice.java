package lotto.domain;

public class InsertPrice {
    private final int price;

    public InsertPrice(int price) {
        if (isNegative(price)) {
            String message = "price cannot be negative.";
            throw new IllegalArgumentException(message);
        }
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    private boolean isNegative(int price) {
        return price < 0;
    }
}
