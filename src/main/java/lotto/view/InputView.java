package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicketIssuer;
import lotto.domain.Price;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {
    public static Price getInputPrice() {
        Price inputPrice = null;
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                inputPrice = new Price(getInt());
                validateInputPrice(inputPrice);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return inputPrice;
    }

    public static LottoNumbers getLuckyNumbers() {
        LottoNumbers luckyNumbers = null;
        while (true) {
            try {
                System.out.println("지난 주 당첨 번호를 입력해 주세요.");
                luckyNumbers = new LottoNumbers(
                        Arrays.stream(getStringArray())
                                .map(String::trim)
                                .map(Integer::parseInt)
                                .map(LottoNumber::new)
                                .collect(Collectors.toList())
                );
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return luckyNumbers;
    }

    public static LottoNumber getBonusNumber(final LottoNumbers luckyNumbers) {
        LottoNumber bonusNumber = null;
        while (true){
            try {
                System.out.println("보너스 볼을 입력해 주세요.");
                bonusNumber = new LottoNumber(getInt());
                validateBonusNumber(luckyNumbers, bonusNumber);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return bonusNumber;
    }

    private static void validateInputPrice(Price inputPrice) {
        if (inputPrice.getPrice() < LottoTicketIssuer.TICKET_PRICE.getPrice()) {
            throw new IllegalArgumentException(inputPrice.getPrice() + " -> 최소 1000원 이상 입력해 주세요.");
        }
    }

    private static void validateBonusNumber(LottoNumbers luckyNumbers, LottoNumber bonusNumber) {
        if (luckyNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(bonusNumber + " -> 보너스 번호와 중복되지 않아야 합니다.");
        }
    }

    private static int getInt() {
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    private static String[] getStringArray() {
        return new Scanner(System.in)
                .nextLine()
                .split(",");
    }
}
