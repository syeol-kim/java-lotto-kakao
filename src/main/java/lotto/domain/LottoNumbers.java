package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LottoNumbers {
    static final int ALLOWED_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        if (areInvalidLottoNumbers(lottoNumbers)) {
            String message = String.format("Size of lottoNumbers should be %d.",
                    ALLOWED_NUMBER_COUNT);
            throw new IllegalArgumentException(message);
        }

        this.lottoNumbers = Collections.unmodifiableList(lottoNumbers);
    }

    public Stream<LottoNumber> toStream() {
        return lottoNumbers.stream();
    }

    private boolean areInvalidLottoNumbers(List<LottoNumber> lottoNumbers) {
        return isInvalidSize(lottoNumbers) || !areAllDistinctNumbers(lottoNumbers);
    }

    private boolean isInvalidSize(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != ALLOWED_NUMBER_COUNT;
    }

    private boolean areAllDistinctNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .distinct()
                .count() == lottoNumbers.size();
    }

    public boolean contains(LottoNumber lottonumber) {
        return lottoNumbers.contains(lottonumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LottoNumbers) {
            LottoNumbers numbers = (LottoNumbers) obj;
            return this.lottoNumbers.containsAll(numbers.lottoNumbers);
        }
        return false;
    }
}
