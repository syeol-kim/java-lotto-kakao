package lotto.domain;

import java.util.Objects;

public class Rate {
    private int rate;

    Rate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate r = (Rate) o;
        return this.rate == r.rate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }
}
