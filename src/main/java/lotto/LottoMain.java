package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

import static lotto.domain.LottoTicketIssuer.LOTTO_PRICE;

public class LottoMain {
    public static void main(String[] args) {
        Price inputPrice = InputView.getInputPrice();
        Count numberOfManualLotto = InputView.getNumberOfManualLotto(inputPrice);
        Count numberOfAutomaticLotto = new Count(inputPrice.divide(LOTTO_PRICE).getPrice() - numberOfManualLotto.getCount());
        List<LottoNumbers> manualLottoList = InputView.getManualLottoList(numberOfManualLotto);

        LottoTicket ticket = LottoTicketIssuer.issue(inputPrice, manualLottoList);
        OutputView.printNumberOfLottoPurchased(numberOfManualLotto, numberOfAutomaticLotto);
        OutputView.printLottoTicket(ticket);

        LottoNumbers luckyNumbers = InputView.getLuckyNumbers();
        LottoNumber bonusNumber = InputView.getBonusNumber(luckyNumbers);
        WinningNumbers winningNumbers = new WinningNumbers(luckyNumbers, bonusNumber);

        LottoMatchingChecker matchingChecker = new LottoMatchingChecker();
        MatchResult matchResult = matchingChecker.getMatchResult(winningNumbers, ticket);

        Price outputPrice = LottoCalculator.getTotalPrice(matchResult);
        Rate rateOfReturn = LottoCalculator.getRateOfReturn(inputPrice, outputPrice);

        OutputView.printStatistics(matchResult, rateOfReturn);
    }
}
