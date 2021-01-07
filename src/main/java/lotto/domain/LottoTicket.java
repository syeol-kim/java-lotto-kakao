package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicket implements NumberPickStrategy {
    private final static List<LottoNumber> digits = IntStream
            .rangeClosed(LottoNumber.LOWER_BOUND, LottoNumber.UPPER_BOUND)
            .boxed()
            .map(LottoNumber::new)
            .collect(Collectors.toList());

    private final LottoNumbers lottoNumbers;

    public LottoTicket() {
        lottoNumbers = generateLottoNumbers();
    }

    public LottoTicket(LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoNumbers generateLottoNumbers() {
        List<LottoNumber> numbers =
                generateRandomLottoNumbers(LottoNumbers.ALLOWED_NUMBER_COUNT);
        return new LottoNumbers(numbers);
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LottoNumbers) {
            LottoNumbers numbers = (LottoNumbers) obj;
            return this.lottoNumbers.equals(numbers);
        }

        if (obj instanceof LottoTicket) {
            LottoTicket ticket = (LottoTicket) obj;
            return this.lottoNumbers.equals(ticket.lottoNumbers);
        }

        return false;
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    private List<LottoNumber> generateRandomLottoNumbers(int size) {
        Collections.shuffle(digits);
        return digits.stream()
                .limit(size)
                .collect(Collectors.toList());
    }
}
