package lotto.domain;

public class WinningNumbers {
    private final LottoNumbers luckyNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers luckyNumbers, LottoNumber bonusNumber) {
        if (isDuplicate(luckyNumbers, bonusNumber)) {
            String message = "Bonus number already exists in lucky numbers";
            throw new IllegalArgumentException(message);
        }
        this.luckyNumbers = luckyNumbers;
        this.bonusNumber = bonusNumber;
    }

    private boolean isDuplicate(LottoNumbers luckyNumbers, LottoNumber bonusNumber) {
        return luckyNumbers.contains(bonusNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WinningNumbers) {
            WinningNumbers winningNumbers = (WinningNumbers) obj;
            return this.luckyNumbers.equals(winningNumbers.luckyNumbers)
                    && this.bonusNumber.equals(winningNumbers.bonusNumber);
        }
        return false;
    }

    public LottoNumbers getLuckyNumbers() {
        return luckyNumbers;
    }

    public LottoNumber getBunusNumber() {
        return bonusNumber;
    }
}
