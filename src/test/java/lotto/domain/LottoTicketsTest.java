package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketsTest {
    @Test
    public void ticketsSuccessfullyIssued() {
        LottoNumbers lottoNumbers = new LottoNumbers(
                IntStream.range(1, 7)
                        .boxed()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()));
        List<LottoTicket> tickets = Arrays.asList(
                new LottoTicket(lottoNumbers),
                new LottoTicket(lottoNumbers),
                new LottoTicket(lottoNumbers));

        LottoTickets lottoTickets = new LottoTickets(tickets);

        assertThat(lottoTickets).isEqualTo(new LottoTickets(tickets));
    }
}
