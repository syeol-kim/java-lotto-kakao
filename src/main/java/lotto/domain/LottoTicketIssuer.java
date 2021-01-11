package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LottoTicketIssuer {
    public static final Price TICKET_PRICE = new Price(1000);
    private static final List<LottoNumber> numberList = IntStream.rangeClosed(
            LottoNumber.LOWER_BOUND, LottoNumber.UPPER_BOUND)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());

    private LottoTicketIssuer() {
    }

    public static LottoTicket issue(Price inputPrice) {
        List<LottoNumbers> numbersList =
                IntStream.range(0,
                        inputPrice.divide(TICKET_PRICE)
                                .getPrice()
                )
                        .mapToObj(x -> createLottoNumbersRandomly())
                        .collect(Collectors.toList());
        return new LottoTicket(numbersList);
    }

    private static LottoNumbers createLottoNumbersRandomly() {
        Collections.shuffle(numberList);
        List<LottoNumber> numbers = numberList.stream()
                .limit(LottoNumbers.REQUIRED_SIZE)
                .collect(Collectors.toList());
        return new LottoNumbers(numbers);
    }
}
