package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {
    public static int getInsertPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(
                new Scanner(System.in).nextLine()
        );
    }

    public static LottoNumbers getLuckyNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<LottoNumber> lottoNumbers = Arrays.stream(
                new Scanner(System.in).nextLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoNumbers(lottoNumbers);
    }

    public static LottoNumber getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = Integer.parseInt(
                new Scanner(System.in).nextLine()
        );
        return new LottoNumber(bonusNumber);
    }
}
