package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoNumbersTest {
    @Test
    public void Should_SucceedInCreatingLottoNumbers() {
        assertThat(new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList())
        )).isInstanceOf(LottoNumbers.class);
    }

    @Test
    public void Should_FailToCreateLottoNumbers_HasSevenNumbers() {
        List<LottoNumber> sevenNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoNumbers(sevenNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 로또 번호가 주어져야 합니다.");
    }

    @Test
    public void Should_FailToCreateLottoNumbers_HasFiveNumbers() {
        List<LottoNumber> fiveNumbers = Stream.of(1, 2, 3, 4, 5)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoNumbers(fiveNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 로또 번호가 주어져야 합니다.");
    }

    @Test
    public void Should_FailToCreateLottoNumbers_NumbersIsDuplicated() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 1, 2, 3, 3, 4)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoNumbers(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1, 3 -> 서로 다른 6개의 숫자여야 합니다.");
    }

    @Test
    public void Should_ReturnNumberOfEqualElements_ForAnotherLottoNumbers() {
        LottoNumbers oneToSix = new LottoNumbers(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        LottoNumbers fourToNine = new LottoNumbers(Stream.of(4, 5, 6, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        assertThat(oneToSix.getNumberOfEqualElements(fourToNine))
                .isEqualTo(new Count(3));
    }

    @Test
    public void Should_IsContains_HaveSameLottoNumber() {
        LottoNumbers oneToSix = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
        LottoNumber one = new LottoNumber(1);

        assertThat(oneToSix.contains(one))
                .isTrue();
    }

    @Test
    public void Should_IsNotContains_HaveNoSameLottoNumber() {
        LottoNumbers oneToSix = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()));
        LottoNumber seven = new LottoNumber(7);

        assertThat(oneToSix.contains(seven))
                .isFalse();
    }

    @Test
    public void Should_IsEqual_HasSameLottoNumbers() {
        List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThat(new LottoNumbers(lottoNumbers))
                .isEqualTo(new LottoNumbers(lottoNumbers));
    }

    @Test
    public void Should_IsNotEqual_HaveDifferentLottoNumber() {
        LottoNumbers oneToSix = new LottoNumbers(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        LottoNumbers twoToSeven = new LottoNumbers(Stream.of(2, 3, 4, 5, 6, 7)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        assertThat(oneToSix)
                .isNotEqualTo(twoToSeven);
    }
}
