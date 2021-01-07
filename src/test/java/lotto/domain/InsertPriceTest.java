package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class InsertPriceTest {
    @Test
    public void fiveThousandWon() {
        InsertPrice price = new InsertPrice(5000);

        assertThat(price.getPrice()).isEqualTo(5000);
    }

    @Test
    public void negativeShouldThrowError() {
        assertThatThrownBy(() -> new InsertPrice(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
