package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketIssuerTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 1999, 2000, 2001})
    public void issueTest(int insertPrice){
        LottoTickets tickets = LottoTicketIssuer.issue(insertPrice);
        assertThat(tickets.getTicketsNum()).isEqualTo(insertPrice/LottoTicketIssuer.TICKET_PRICE);
    }
}
