package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        int investment = InputView.getInsertPrice();
        InsertPrice insertPrice = new InsertPrice(investment);
        OutputView.printLottoTicketNum(insertPrice.getPrice());

        LottoTickets tickets = LottoTicketIssuer.issue(insertPrice.getPrice());
        OutputView.printLottoTickets(tickets);

        LottoNumbers luckyNumber = InputView.getLuckyNumber();
        LottoNumber bonusNumber = InputView.getBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(luckyNumber, bonusNumber);

        LottoMatcher lottoMatcher = new LottoMatcher(winningNumbers);
        MatchResults matchResults = lottoMatcher.match(tickets);

        LottoStatistics statistics = new LottoStatistics(matchResults, insertPrice);
        OutputView.printStatistics(statistics);
    }
}
