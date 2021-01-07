package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LottoTickets {
    private final List<LottoTicket> tickets;

    public LottoTickets(List<LottoTicket> tickets) {
        this.tickets = Collections.unmodifiableList(tickets);
    }

    public int getTicketsNum() {
        return tickets.size();
    }

    public Stream<LottoTicket> toStream() {
        return tickets.stream();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LottoTickets)) {
            return false;
        }

        LottoTickets numbers = (LottoTickets) obj;
        if (getTicketsNum() != numbers.getTicketsNum()) {
            return false;
        }

        return this.tickets.containsAll(numbers.tickets);
    }
}
