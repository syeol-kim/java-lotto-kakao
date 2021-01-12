package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static lotto.domain.LottoTicketIssuer.LOTTO_PRICE;

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

    public static Count getNumberOfManualLotto(Price inputPrice) {
        Count numberOfManualLotto = null;
        while (true) {
            try {
                System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
                numberOfManualLotto = new Count(getInt());
                validateNumberOfManualLotto(inputPrice, numberOfManualLotto);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return numberOfManualLotto;
    }

    private static void validateNumberOfManualLotto(Price inputPrice, Count numberOfManualLotto) {
        Count maxNumberOfLotto = new Count(inputPrice.divide(LOTTO_PRICE).getPrice());
        if (maxNumberOfLotto.getCount() < numberOfManualLotto.getCount()) {
            String message = String.format("잔액이 부족합니다. 최대 %d개 구매 가능합니다.", maxNumberOfLotto.getCount());
            throw new IllegalArgumentException(message);
        }
    }

    public static List<LottoNumbers> getManualLottoList(Count numberOfManualLotto) {
        if(numberOfManualLotto.getCount() == 0) {
            return null;
        }

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LottoNumbers> manualLottoList = new ArrayList<>();
        while (manualLottoList.size() < numberOfManualLotto.getCount()) {
            try {
                LottoNumbers lottoNumbers = new LottoNumbers(
                        Arrays.stream(getStringArray())
                                .map(String::trim)
                                .map(Integer::parseInt)
                                .map(LottoNumber::new)
                                .collect(Collectors.toList())
                );
                manualLottoList.add(lottoNumbers);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return manualLottoList;
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
        if (inputPrice.getPrice() < LOTTO_PRICE.getPrice()) {
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
