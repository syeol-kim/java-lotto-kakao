package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketTest {
    @Test
    public void generateValidTicket() {
        LottoTicket ticket = new LottoTicket() {
            @Override
            public LottoNumbers generateLottoNumbers() {
                return new LottoNumbers(Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()));
            }
        };

        assertThat(ticket.getLottoNumbers()).isEqualTo(
                new LottoNumbers(Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList()))
        );
    }
}
