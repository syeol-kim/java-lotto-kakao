package lotto.domain;

import java.util.Objects;

public class Count {
    private int count;

    public Count() {
        this(0);
    }

    public Count(int count) {
        validate(count);
        this.count = count;
    }

    public void increase() {
        if (isMaximum()) {
            String message = String.format("카운트는 %d보다 클 수 없습니다.", Integer.MAX_VALUE);
            throw new IllegalStateException(message);
        }
        ++count;
    }

    public void decrease() {
        if (isMinimum()) {
            String message = String.format("카운트는 %d보다 작을 수 없습니다.", 0);
            throw new IllegalStateException(message);
        }
        --count;
    }

    private void validate(int count) {
        if (isNegative(count)) {
            String message = String.format("%d -> 카운트는 0보다 크거나 같아야 합니다.", count);
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isNegative(int count) {
        return count < 0;
    }

    private boolean isMaximum() {
        return count == Integer.MAX_VALUE;
    }

    private boolean isMinimum() {
        return count == 0;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return Integer.toString(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Count c = (Count) o;
        return this.count == c.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
