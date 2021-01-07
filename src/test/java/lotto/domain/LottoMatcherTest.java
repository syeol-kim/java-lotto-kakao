package lotto.domain;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class lottoNumberArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(lottoNumberArgument(1, 2, 3, 4, 5, 6), MatchResult.FIRST),
                Arguments.of(lottoNumberArgument(1, 2, 3, 4, 5, 7), MatchResult.SECOND),
                Arguments.of(lottoNumberArgument(1, 2, 3, 4, 5, 8), MatchResult.THIRD),
                Arguments.of(lottoNumberArgument(1, 2, 3, 4, 8, 9), MatchResult.FOURTH),
                Arguments.of(lottoNumberArgument(1, 2, 3, 8, 9, 10), MatchResult.FIFTH),
                Arguments.of(lottoNumberArgument(1, 2, 8, 9, 10, 11), MatchResult.NULL)
        );
    }

    private List<LottoNumber> lottoNumberArgument(int... parameters) {
        return Stream.of(parameters[0], parameters[1], parameters[2],
                parameters[3], parameters[4], parameters[5])
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}

public class LottoMatcherTest {
    @ParameterizedTest
    @ArgumentsSource(lottoNumberArgumentsProvider.class)
    public void testRanks(List<LottoNumber> numbers, MatchResult expected) {
        List<LottoNumber> answer = Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        LottoNumbers luckyNumbers = new LottoNumbers(answer);
        LottoNumber bonusNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(luckyNumbers, bonusNumber);
        LottoMatcher matcher = new LottoMatcher(winningNumbers);

        assertThat(matcher.match(
                new LottoTicket(new LottoNumbers(numbers))
        )).isEqualTo(expected);
    }
}
