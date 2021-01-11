package lotto.domain;

import java.util.Objects;

public class Price {
    private final int price;

    public Price() {
        this(0);
    }

    public Price(int price) {
        validate(price);
        this.price = price;
    }

    private void validate(int price) {
        if (isNegative(price)) {
            throw new IllegalArgumentException(price + " -> 가격은 0보다 크거나 같은 정수여야 합니다.");
        }
    }

    private boolean isNegative(int price) {
        return price < 0;
    }

    public Price add(Price p) {
        return new Price(price + p.price);
    }

    public Price divide(Price p) {
        return new Price(price / p.price);
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return Integer.toString(price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price p = (Price) o;
        return this.price == p.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
