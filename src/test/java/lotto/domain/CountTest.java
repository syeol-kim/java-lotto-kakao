package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CountTest {
    @Test
    public void Should_SucceedInCreatingCount() {
        Count count = new Count();
        assertThat(count.getCount())
                .isEqualTo(0);
    }

    @Test
    public void Should_SucceedInIncreasingCount() {
        Count count = new Count();
        count.increase();
        assertThat(count.getCount())
                .isEqualTo(1);
    }

    @Test
    public void Should_SucceedInDecreasingCount() {
        Count count = new Count(1);
        count.decrease();
        assertThat(count.getCount())
                .isEqualTo(0);
    }

    @Test
    public void Should_FailToCreateCount_CountIsNegative() {
        assertThatThrownBy(() -> new Count(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("-1 -> 카운트는 0보다 크거나 같아야 합니다.");
    }

    @Test
    public void Should_FailToIncreaseCount_CountIsMaximum() {
        Count count = new Count(Integer.MAX_VALUE);
        assertThatThrownBy(count::increase)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(
                        String.format("카운트는 %d보다 클 수 없습니다.", Integer.MAX_VALUE));
    }

    @Test
    public void Should_FailToDecreaseCount_CountIsMinimum() {
        Count count = new Count(0);
        assertThatThrownBy(count::decrease)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카운트는 0보다 작을 수 없습니다.");
    }

    @Test
    public void Should_IsEqual_HaveSameCount() {
        Count count = new Count(1);
        assertThat(count)
                .isEqualTo(new Count(1));
    }

    @Test
    public void Should_IsNotEqual_HaveDifferentCount() {
        Count count = new Count();
        assertThat(count)
                .isNotEqualTo(new Count(1));
    }
}
