package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    public static final int REQUIRED_SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoNumbers(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = Collections.unmodifiableList(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (isInvalidSize(numbers)) {
            String message = String.format("%d개의 로또 번호가 주어져야 합니다.", REQUIRED_SIZE);
            throw new IllegalArgumentException(message);
        }

        if (isDuplicated(numbers)) {
            String elements = getDuplicatedElements(numbers).stream()
                    .map(LottoNumber::toString)
                    .collect(Collectors.joining(", "));
            String message = String.format("%s -> 서로 다른 %d개의 숫자여야 합니다.",
                    elements, REQUIRED_SIZE);
            throw new IllegalArgumentException(message);
        }
    }

    private boolean isInvalidSize(List<LottoNumber> numbers) {
        return numbers.size() != REQUIRED_SIZE;
    }

    private boolean isDuplicated(List<LottoNumber> numbers) {
        return numbers.stream()
                .distinct()
                .count() != numbers.size();
    }

    private List<LottoNumber> getDuplicatedElements(List<LottoNumber> numbers) {
        return numbers.stream()
                .distinct()
                .filter(number ->
                        numbers.indexOf(number) != numbers.lastIndexOf(number))
                .collect(Collectors.toList());
    }

    public Count getNumberOfEqualElements(LottoNumbers lottoNumbers) {
        return new Count(
                (int) numbers.stream()
                        .filter(lottoNumbers.numbers::contains)
                        .count());
    }

    @Override
    public String toString() {
        return "[" + numbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", ")) + "]";
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LottoNumbers) {
            LottoNumbers lottoNumbers = (LottoNumbers) obj;
            return this.numbers.containsAll(lottoNumbers.numbers)
                    && this.numbers.size() == lottoNumbers.numbers.size();
        }
        return false;
    }
}
