package lotto.domain;

public class WinningNumbers {
    private final LottoNumbers luckyNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers luckyNumbers, LottoNumber bonusNumber) {
        validate(luckyNumbers, bonusNumber);
        this.luckyNumbers = luckyNumbers;
        this.bonusNumber = bonusNumber;
    }

    public void validate(LottoNumbers luckyNumbers, LottoNumber bonusNumber) {
        if (isDuplicate(luckyNumbers, bonusNumber)) {
            throw new IllegalArgumentException(bonusNumber + " -> 보너스 번호와 중복되지 않아야 합니다.");
        }
    }

    private boolean isDuplicate(LottoNumbers luckyNumbers, LottoNumber bonusNumber) {
        return luckyNumbers.contains(bonusNumber);
    }

    public Count getNumberOfElementsInLuckyNumbers(LottoNumbers lottoNumbers) {
        return luckyNumbers.getNumberOfEqualElements(lottoNumbers);
    }

    public boolean matchBonusNumber(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }
}
