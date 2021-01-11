package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PriceTest {
    @Test
    public void Should_SucceedInCreatingPrice() {
        Price price = new Price();
        assertThat(price.getPrice())
                .isEqualTo(0);
    }

    @Test
    public void Should_SucceedInAddPrice() {
        Price price = new Price(1000);
        price = price.add(new Price(2500));
        assertThat(price.getPrice())
                .isEqualTo(3500);
    }

    @Test
    public void Should_SucceedInDividePrice() {
        Price price = new Price(5000);
        price = price.divide(new Price(1000));
        assertThat(price.getPrice())
                .isEqualTo(5);
    }

    @Test
    public void Should_FailToCreatePrice_PriceIsNegative() {
        assertThatThrownBy(() -> new Price(-100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("-100 -> 가격은 0보다 크거나 같은 정수여야 합니다.");
    }

    @Test
    public void Should_IsEqual_HaveSamePrice() {
        Price price = new Price(1000);
        assertThat(price)
                .isEqualTo(new Price(1000));
    }

    @Test
    public void Should_IsNotEqual_HaveDifferentPrice() {
        Price price = new Price(1000);
        assertThat(price)
                .isNotEqualTo(new Price(2000));
    }
}
