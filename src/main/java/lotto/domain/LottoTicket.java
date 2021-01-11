package lotto.domain;

import java.util.List;

public class LottoTicket {
    private final List<LottoNumbers> numbersList;

    public LottoTicket(List<LottoNumbers> numbersList) {
        validate(numbersList);
        this.numbersList = numbersList;
    }

    private void validate(List<LottoNumbers> numbersList) {
        if (isEmpty(numbersList)) {
            throw new IllegalArgumentException("티켓은 최소 1개 이상의 로또 번호들을 가져야 합니다.");
        }
    }

    private boolean isEmpty(List<LottoNumbers> numbersList) {
        return numbersList == null || numbersList.isEmpty();
    }

    public List<LottoNumbers> getLottoNumbersList() {
        return numbersList;
    }
}
