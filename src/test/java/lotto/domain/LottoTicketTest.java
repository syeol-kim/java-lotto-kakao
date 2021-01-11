package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketTest {
    @Test
    public void Should_SucceedInCreatingLottoTicket() {
        List<LottoNumbers> numbersList = Arrays.asList(
                new LottoNumbers(Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
                ),
                new LottoNumbers(Stream.of(7, 8, 9, 10, 11, 12)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
                ),
                new LottoNumbers(Stream.of(13, 14, 15, 16, 17, 18)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
                )
        );

        assertThat(new LottoTicket(numbersList))
                .isInstanceOf(LottoTicket.class);
    }

    @Test
    public void Should_FailToCreateLottoTicket_HasNoLottoNumbers() {
        List<LottoNumbers> numbersList = new ArrayList<>();
        assertThatThrownBy(() -> new LottoTicket(numbersList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("티켓은 최소 1개 이상의 로또 번호들을 가져야 합니다.");
    }

    @Test
    public void Should_ReturnLottoNumbersList() {
        List<LottoNumbers> numbersList = Arrays.asList(
                new LottoNumbers(Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
                ),
                new LottoNumbers(Stream.of(7, 8, 9, 10, 11, 12)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
                ),
                new LottoNumbers(Stream.of(13, 14, 15, 16, 17, 18)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
                )
        );
        LottoTicket ticket = new LottoTicket(numbersList);

        assertThat(ticket.getLottoNumbersList())
                .isEqualTo(numbersList);
    }
}
