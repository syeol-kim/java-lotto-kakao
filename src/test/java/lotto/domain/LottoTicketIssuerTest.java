package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketIssuerTest {
    @ParameterizedTest
    @ValueSource(ints = {1000, 1999, 2000, 2001})
    public void Should_SucceedInIssuance_WithoutManualLottoList(int price) {
        Price inputPrice = new Price(price);
        LottoTicket ticket = LottoTicketIssuer.issue(inputPrice, null);
        assertThat(ticket)
                .isInstanceOf(LottoTicket.class);
        assertThat(ticket.getLottoNumbersList().size())
                .isEqualTo(inputPrice.getPrice() / 1000);  // inputPrice / LOTTO_PRICE
    }

    @Test
    public void Should_SucceedInIssuance_WithManualLottoList() {
        Price inputPrice = new Price(5500);
        LottoNumbers first = new LottoNumbers(
                Stream.of(1, 2, 3, 4, 5, 6)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
        LottoNumbers second = new LottoNumbers(
                Stream.of(2, 3, 4, 5, 6, 7)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );

        List<LottoNumbers> manualLottoList = Arrays.asList(
                first, second);
        LottoTicket ticket = LottoTicketIssuer.issue(inputPrice, manualLottoList);

        assertThat(ticket)
                .isInstanceOf(LottoTicket.class);
        assertThat(ticket.getLottoNumbersList().size())
                .isEqualTo(5);  // inputPrice / LOTTO_PRICE
        assertThat(ticket.getLottoNumbersList().get(0))
                .isEqualTo(first);
        assertThat(ticket.getLottoNumbersList().get(1))
                .isEqualTo(second);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 100, 500, 999})
    public void Should_FailToIssue_InputPriceLessThanThousand_WithoutManualLottoList(int price) {
        Price inputPrice = new Price(price);
        assertThatThrownBy(() -> LottoTicketIssuer.issue(inputPrice, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("티켓은 최소 1개 이상의 로또 번호들을 가져야 합니다.");
    }

    @Test
    public void Should_FailToIssue_InputPriceLessThanThousand_WithManualLottoList() {
        Price inputPrice = new Price(500);
        List<LottoNumbers> manualLottoList = Arrays.asList(
                new LottoNumbers(
                        Stream.of(1, 2, 3, 4, 5, 6)
                                .map(LottoNumber::new)
                                .collect(Collectors.toList())
                ),
                new LottoNumbers(
                        Stream.of(2, 3, 4, 5, 6, 7)
                                .map(LottoNumber::new)
                                .collect(Collectors.toList())
                )
        );

        assertThatThrownBy(() -> LottoTicketIssuer.issue(inputPrice, manualLottoList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("-1500원 -> 금액이 부족합니다.");
    }

    @Test
    public void Should_FailToIssue_InputPriceIsNotEnough() {
        Price inputPrice = new Price(2500);
        List<LottoNumbers> manualLottoList = Arrays.asList(
                new LottoNumbers(
                        Stream.of(1, 2, 3, 4, 5, 6)
                                .map(LottoNumber::new)
                                .collect(Collectors.toList())
                ),
                new LottoNumbers(
                        Stream.of(2, 3, 4, 5, 6, 7)
                                .map(LottoNumber::new)
                                .collect(Collectors.toList())
                ),
                new LottoNumbers(
                        Stream.of(3, 4, 5, 6, 7, 8)
                                .map(LottoNumber::new)
                                .collect(Collectors.toList())
                )
        );

        assertThatThrownBy(() -> LottoTicketIssuer.issue(inputPrice, manualLottoList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("-500원 -> 금액이 부족합니다.");
    }
}
