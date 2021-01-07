package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class LottoTicketIssuer {
    public static final int TICKET_PRICE = 1000;

    private LottoTicketIssuer() {}

    public static LottoTickets issue(int insertPrice) {
        List<LottoTicket> tickets = IntStream.range(0, insertPrice / TICKET_PRICE)
                .boxed()
                .map(x -> new LottoTicket())
                .collect(Collectors.toList());
        return new LottoTickets(tickets);
    }
}
