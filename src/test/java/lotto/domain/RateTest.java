package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RateTest {
    @Test
    public void Should_IsEqual_HaveSameRate() {
        Rate rate = new Rate(100);
        assertThat(rate)
                .isEqualTo(new Rate(100));
    }

    @Test
    public void Should_IsNotEqual_HaveDifferentRate() {
        Rate rate = new Rate(100);
        assertThat(rate)
                .isNotEqualTo(new Rate(200));
    }
}
