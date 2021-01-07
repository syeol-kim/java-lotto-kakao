package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoNumbersTest {
    @Test
    public void isEqualAndValid() {
        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);

        assertThat(lottoNumbers)
                .isEqualTo(new LottoNumbers(numbers));
    }

    @Test
    public void duplicateNumbersShouldThrowError() {
        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5, 5)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void fiveElementsShouldThrowError() {
        List<LottoNumber> numbers = Stream.of(1, 2, 3, 4, 5)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new LottoNumbers(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
