package kr.co.nicepay.untact.common.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static kr.co.nicepay.untact.common.types.PaymentMethod.PaymentType.*;

@Slf4j
@Getter
@ToString
@RequiredArgsConstructor
public enum PaymentMethod {

    ALL("all", "00", AUTH_PAY),
    /* 일반 결제 */
    CARD("card", "01", AUTH_PAY),
    DIRECT_CARD("directCard", "01", AUTH_PAY),
    BANK("bank", "02", AUTH_PAY),
    VBANK("vbank", "03", AUTH_PAY),
    CELLPHONE("cellphone", "05", AUTH_PAY),
    /* 일반 + 간편결제*/
    CARD_AND_EASY_PAY("cardAndEasyPay", "01", AUTH_PAY),
    /* 간편결제 */
    NAVER_PAY_CARD("naverpayCard", "05", EASY),
    KAKAO_PAY("kakaopay", "01", EASY),
    KAKAO_PAY_CARD("kakaopayCard", "01", EASY),
    KAKAO_PAY_MONEY("kakaopayMoney", "01", EASY),
    PAYCO("payco", "01", EASY),
    SSGPAY("ssgpay", "24", EASY),
    SAMSUNG_PAY_CARD("samsungpayCard", "01", EASY),
    /* 비인증(빌링) 결제 */
    CARD_BILL("cardBill", "01", NON_AUTH_PAY),
    /* 현금 영수증 */
    CASH_RECEIPT("cashrcpt", "01", RECEIPT),
    /* method에 정의되지 않은 테스트 method */
    BLANK_METHOD(" ", "00", FAIL),
    EMPTY_METHOD("", "00", FAIL);

    public static final List<String> METHOD_ALL = Arrays.stream(values())
            .filter(paymentMethod -> !paymentMethod.getType().equals(FAIL))
            .map(PaymentMethod::getTitle)
            .toList();
    /* 간편결제 정의 */
    public static final List<PaymentMethod> ONLY_EASY_PAY_METHODS = Arrays.stream(values())
            .filter(paymentMethod -> paymentMethod.getType().equals(EASY))
            .toList();
    /* 간편결제 정의 */
    public static final List<PaymentMethod> EASY_PAY_AND_CARD_AND_EASY_PAY_METHODS = Stream
            .concat(ONLY_EASY_PAY_METHODS.stream(), Stream.of(CARD_AND_EASY_PAY))
            .toList();
    /* 간편결제 포함 다이렉트 카드 정의 */
    private static final List<PaymentMethod> DIRECT_AND_EASY_PAY_METHODS = Stream
            .concat(EASY_PAY_AND_CARD_AND_EASY_PAY_METHODS.stream(), Stream.of(DIRECT_CARD))
            .toList();
    private static final List<PaymentMethod> GENERAL_PAY_METHODS = Arrays.stream(values())
            .filter(paymentMethod -> paymentMethod.getType().equals(AUTH_PAY))
            .toList();
    private final String title;
    private final String code;
    private final PaymentType type;

    @JsonCreator
    public static PaymentMethod of(String title) {
        log.info("{}", title);
        return Arrays.stream(values())
                .filter(paymentMethod -> paymentMethod.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Payment Method " + title));
    }

    /* 결제수단 포함 여부 확인 */
    public static boolean isMatches(PaymentMethod method, PaymentMethod... expected) {
        return Arrays.asList(expected).contains(method);
    }

    /* 문자열로 결제수단 포함 여부 확인 */
    public static boolean isMatchesByTitle(String method, PaymentMethod... expected) {
        return Arrays.stream(expected)
                .anyMatch(paymentMethod -> paymentMethod.getTitle().equalsIgnoreCase(method));
    }

    /* [간편결제] 포함 여부 확인 */
    public static boolean containsWithEasyPay(PaymentMethod method) {
        return EASY_PAY_AND_CARD_AND_EASY_PAY_METHODS.stream()
                .anyMatch(paymentMethod -> paymentMethod.equals(method));
    }

    /* [간편결제] 포함 여부 문자열 확인 */
    public static boolean containsWithEasyPay(String method) {
        return EASY_PAY_AND_CARD_AND_EASY_PAY_METHODS.stream()
                .anyMatch(paymentMethod -> paymentMethod.getTitle().equalsIgnoreCase(method));
    }

    /* [다이렉트 및 간편결제] 포함 여부 확인 */
    public static boolean containsWithDirectAndEasyPay(PaymentMethod method) {
        return DIRECT_AND_EASY_PAY_METHODS.stream()
                .anyMatch(paymentMethod -> paymentMethod.equals(method));
    }

    /* [다이렉트 및 간편결제] 포함 여부 문자열 확인 */
    public static boolean containsWithDirectAndEasyPay(String method) {
        return DIRECT_AND_EASY_PAY_METHODS.stream()
                .anyMatch(paymentMethod -> paymentMethod.getTitle().equalsIgnoreCase(method));
    }

    @JsonValue
    public String getTitle() {
        return title;
    }

    public enum PaymentType {
        EASY,
        AUTH_PAY,
        NON_AUTH_PAY,
        RECEIPT,
        FAIL
    }
}
