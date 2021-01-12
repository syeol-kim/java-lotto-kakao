package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LottoTicketIssuer {
    public static final Price LOTTO_PRICE = new Price(1000);
    private static final List<LottoNumber> numberList = IntStream.rangeClosed(
            LottoNumber.LOWER_BOUND, LottoNumber.UPPER_BOUND)
            .mapToObj(LottoNumber::new)
            .collect(Collectors.toList());

    private LottoTicketIssuer() {
    }

    public static LottoTicket issue(Price inputPrice, List<LottoNumbers> numbersList) {
        validate(inputPrice, numbersList);
        if (numbersList == null) {
            numbersList = new ArrayList<>();
        }

        numbersList.addAll(
                IntStream.range(0, inputPrice.divide(LOTTO_PRICE)
                        .getPrice() - numbersList.size()
                )
                        .mapToObj(x -> createLottoNumbersRandomly())
                        .collect(Collectors.toList())
        );
        return new LottoTicket(numbersList);
    }

    private static LottoNumbers createLottoNumbersRandomly() {
        Collections.shuffle(numberList);
        List<LottoNumber> numbers = numberList.stream()
                .limit(LottoNumbers.REQUIRED_SIZE)
                .collect(Collectors.toList());
        return new LottoNumbers(numbers);
    }

    private static void validate(Price inputPrice, List<LottoNumbers> manualLottoList) {
        if (isNotEnoughPrice(inputPrice, manualLottoList)) {
            Price difference = new Price(
                    manualLottoList.size() * LOTTO_PRICE.getPrice() - inputPrice.getPrice()
            );
            throw new IllegalArgumentException("-" + difference.getPrice() + "원 -> 금액이 부족합니다.");
        }
    }

    private static boolean isNotEnoughPrice(Price inputPrice, List<LottoNumbers> manualLottoList) {
        return !isEmptyList(manualLottoList) && inputPrice.divide(LOTTO_PRICE).getPrice() < manualLottoList.size();
    }

    private static boolean isEmptyList(List<?> list) {
        return list == null || list.isEmpty();
    }
}
