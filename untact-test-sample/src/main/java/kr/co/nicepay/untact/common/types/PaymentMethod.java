package kr.co.nicepay.untact.common.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@Getter
@ToString
@RequiredArgsConstructor
public enum PaymentMethod {

    /* 일반 결제 */
    CARD("card", "01"),
    DIRECT_CARD("directCard", "01"),
    BANK("bank", "02"),
    VBANK("vbank", "03"),
    CELLPHONE("cellphone", "05"),
    /* 일반 + 간편결제*/
    CARD_AND_EASY_PAY("cardAndEasyPay", "01"),
    /* 간편결제 */
    NAVER_PAY_CARD("naverpayCard", "05"),
    KAKAO_PAY("kakaopay", "01"),
    KAKAO_PAY_CARD("kakaopayCard", "01"),
    KAKAO_PAY_MONEY("kakaopayMoney", "01"),
    SAMSUNG_PAY_CARD("samsungpayCard", "01"),
    /* 현금 영수증 */
    CASH_RECEIPT("cashrcpt", "01");

    private final String title;
    private final String code;

    @JsonCreator
    public static PaymentMethod of(String title) {
        log.info("{}", title);
        return Arrays.stream(values())
                .filter(paymentMethod -> paymentMethod.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Payment Method " + title));
    }

    @JsonValue
    public String getTitle() {
        return title;
    }
}
