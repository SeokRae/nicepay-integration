package kr.co.nicepay.untact.subscribe.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class EncData {

    private final String value;

    @Builder
    public EncData(String cardNo, String expYear, String expMonth, String idNo, String cardPw) {

        this.value = "cardNo=" + cardNo + "&" + "expYear=" + expYear + "&" + "expMonth=" + expMonth + "&" + "idNo=" + idNo + "&" + "cardPw=" + cardPw;

    }
}
