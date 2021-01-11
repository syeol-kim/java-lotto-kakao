package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class WinningNumbersTest {
    @Test
    public void Should_SucceedInCreatingWinningNumber() {
        LottoNumbers luckyNumbers = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList())
        );
        LottoNumber bonusNumber = new LottoNumber(7);

        assertThat(new WinningNumbers(luckyNumbers, bonusNumber))
                .isInstanceOf(WinningNumbers.class);
    }

    @Test
    public void Should_FailToCreateWinningNumber_IsDuplicatedBetweenLuckyNumbersAndBonusNumber() {
        LottoNumbers luckyNumbers = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
        LottoNumber bonusNumber = new LottoNumber(1);

        assertThatThrownBy(() -> new WinningNumbers(luckyNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1 -> 보너스 번호와 중복되지 않아야 합니다.");
    }

    @Test
    public void Should_MatchBonusNumber_IsDuplicatedBetweenLottoNumbersAndBonusNumber() {
        LottoNumbers luckyNumbers = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(luckyNumbers, bonusNumber);

        LottoNumbers lottoNumbers = new LottoNumbers(
                Stream.of(4, 5, 6, 7, 8, 9)
                .map(LottoNumber::new)
                .collect(Collectors.toList())
        );

        assertThat(winningNumbers.matchBonusNumber(lottoNumbers))
                .isTrue();
    }

    @Test
    public void Should_NotMatchBonusNumber_IsNotDuplicatedBetweenLottoNumbersAndBonusNumber() {
        LottoNumbers luckyNumbers = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(luckyNumbers, bonusNumber);

        LottoNumbers lottoNumbers = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );

        assertThat(winningNumbers.matchBonusNumber(lottoNumbers))
                .isFalse();
    }

    @Test
    public void Should_ReturnNumberOfElementsInLuckyNumbers_ForLottoNumbers(){
        LottoNumbers luckyNumbers = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(luckyNumbers, bonusNumber);

        LottoNumbers lottoNumbers = new LottoNumbers(
                Stream.of(4, 5, 6, 7, 8, 9)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );

        assertThat(winningNumbers.getNumberOfElementsInLuckyNumbers(lottoNumbers))
                .isEqualTo(new Count(3));
    }
}
