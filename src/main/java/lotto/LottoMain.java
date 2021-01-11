package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        Price inputPrice = InputView.getInputPrice();
        OutputView.printNumberOfLottoPurchased(inputPrice);

        LottoTicket ticket = LottoTicketIssuer.issue(inputPrice);
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
