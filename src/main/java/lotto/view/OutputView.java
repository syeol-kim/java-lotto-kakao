package lotto.view;

import lotto.domain.*;

import java.util.stream.Collectors;

public class OutputView {
    public static void printNumberOfLottoPurchased(Price inputPrice) {
        int number = inputPrice.divide(LottoTicketIssuer.TICKET_PRICE).getPrice();
        System.out.println(number + "개를 구매했습니다.");
    }

    public static void printLottoTicket(LottoTicket lottoTicket) {
        String message = lottoTicket.getLottoNumbersList()
                .stream()
                .map(LottoNumbers::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(message + System.lineSeparator());
    }

    public static void printStatistics(MatchResult matchResult, Rate rateOfReturn) {
        StringBuilder message = new StringBuilder();

        message.append("당첨 통계")
                .append(System.lineSeparator())
                .append("---------")
                .append(System.lineSeparator());

        for (Rank rank : Rank.values()) {
            message.append(rank.getMatchCount())
                    .append("개 일치 (")
                    .append(rank.getPrize())
                    .append("원) - ")
                    .append(matchResult.getCount(rank))
                    .append("개")
                    .append(System.lineSeparator());
        }

        message.append("총 수익률은 ")
                .append(rateOfReturn.getRate())
                .append("% 입니다.");

        System.out.println(message);
    }
}
