package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    public void Should_SucceedInCreatingLottoNumber(int number) {
        assertThat(new LottoNumber(number))
                .isInstanceOf(LottoNumber.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    public void Should_FailToCreateLottoNumber_NumberIsOutOfRange(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(number + " -> 로또 번호는 1~45 사이의 정수여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    public void Should_IsEqual_HaveSameNumber(int number) {
        assertThat(new LottoNumber(number))
                .isEqualTo(new LottoNumber(number));
    }

    @Test
    public void Should_IsNotEqual_HaveDifferentNumber() {
        assertThat(new LottoNumber(1))
                .isNotEqualTo(new LottoNumber(45));
    }
}
