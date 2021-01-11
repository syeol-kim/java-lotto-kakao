package lotto.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketIssuerTest {
    @ParameterizedTest
    @ValueSource(ints = {1000, 1999, 2000, 2001})
    public void Should_SucceedInIssuance(int price) {
        Price inputPrice = new Price(price);
        LottoTicket ticket = LottoTicketIssuer.issue(inputPrice);
        assertThat(ticket)
                .isInstanceOf(LottoTicket.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, 500, 999})
    public void Should_FailToIssue_InputPriceLessThanThousand(int price) {
        Price inputPrice = new Price(price);
        assertThatThrownBy(() -> LottoTicketIssuer.issue(inputPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("티켓은 최소 1개 이상의 로또 번호들을 가져야 합니다.");
    }
}
