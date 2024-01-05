package kr.co.nicepay.untact.subscribe.dto;


import kr.co.nicepay.untact.common.domain.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@ToString
public class SubscribePaymentsRequest {

    /* Mandatory */
    private final Id<String> orderId;
    private final BigDecimal amount;
    private final String goodsName;
    private final int cardQuota;
    private final boolean useShopInterest;

    /* Optional */
    private final String buyerName;
    private final String buyerEmail;
    private final String buyerTel;
    private final BigDecimal taxFreeAmt;
    private final String mallReserved;
    private final String returnCharSet;

    /* Condition */
    private final String ediDate;
    private final String signData;

    @Builder
    public SubscribePaymentsRequest(
            /* Mandatory */
            Id<String> orderId, BigDecimal amount, String goodsName, int cardQuota, boolean useShopInterest,
            /* Optional */
            String buyerName, String buyerEmail, String buyerTel, BigDecimal taxFreeAmt, String mallReserved, String returnCharSet,
            /* Condition */
            String ediDate, String signData
    ) {
        SubscribePaymentValidator.validator(
                orderId, amount, goodsName, cardQuota,
                buyerName, buyerEmail, buyerTel, taxFreeAmt, mallReserved, returnCharSet,
                ediDate, signData
        );
        this.orderId = orderId;
        this.amount = amount;
        this.goodsName = goodsName;
        this.cardQuota = cardQuota;
        this.useShopInterest = useShopInterest; // 무이자 할부 사용 여부 (true: 사용, false: 미사용)
        this.buyerName = buyerName;
        this.buyerEmail = buyerEmail;
        this.buyerTel = buyerTel;
        this.taxFreeAmt = taxFreeAmt;
        this.mallReserved = mallReserved;
        this.returnCharSet = returnCharSet;
        this.ediDate = ediDate;
        this.signData = signData;
    }

    public static class SubscribePaymentValidator {
        private SubscribePaymentValidator() {
        }

        public static void validator(
                Id<String> orderId, BigDecimal amount, String goodsName, int cardQuota,
                String buyerName, String buyerEmail, String buyerTel, BigDecimal taxFreeAmt, String mallReserved,
                String ediDate, String signData, String returnCharSet
        ) {

            // 주문번호 필수, 1 ~ 64자리 이내
            validOrderId(orderId);
            // 금액 필수, 100원 이상 100억 미만
            validAmount(amount);
            // 상품명 필수, 40자 이하
            validGoodsName(goodsName);
            // 할부개월수 필수, 0(일시불) 또는 2 ~ 12 (PG사 가입할 때 설정한 최대 할부개월수), ** 1입력 시 0으로 처리
            validCardQuota(cardQuota);
            // 구매자 이름 선택, 30자 이하
            validBuyerName(buyerName);
            // 구매자 이메일 선택, 60자 이하
            validBuyerEmail(buyerEmail);
            // 구매자 전화번호 선택, 20자 이하 ('-' 제외)
            validBuyerTel(buyerTel);
            // 면세공급가액 선택, 결제금액보다 작아야 함, 100억 미만
            validTaxFreeAmt(taxFreeAmt, amount);
            // 가맹점 예약 필드 선택, 500자 이하 (JSON string format, '"' 사용불가)
            validMallReserved(mallReserved);
            // 전문생성일시, signData 필드를 사용하는 경우 조건적으로 필수값, 그외 선택 (ISO8601 형식인지 확인)
            validEdiDate(ediDate, signData);
            // 리턴 인코딩 방식 선택 (기본값: UTF-8)
            validReturnCharSet(returnCharSet);
            // 위변조 검증Data optional (256자 이하, 생성규칙 : hex(sha256(orderId + bid + ediDate + SecretKey)))
            validSignData(signData);


        }

        private static void validSignData(String signData) {
            if (Strings.isNotEmpty(signData) && signData.length() > 256) {
                throw new IllegalArgumentException("signData is 256 characters");
            }

            if (Strings.isNotEmpty(signData) && !signData.matches("^[0-9a-fA-F]+$")) {
                throw new IllegalArgumentException("signData is not hex");
            }
        }

        private static void validReturnCharSet(String returnCharSet) {
            // 리턴 인코딩 방식 선택 (기본값: UTF-8)
            if (Strings.isNotEmpty(returnCharSet)) {
                if (!returnCharSet.matches("^(UTF-8|EUC-KR|ISO-8859-1)$")) {
                    throw new IllegalArgumentException("returnCharSet is not UTF-8 or EUC-KR or ISO-8859-1");
                }
            }
        }

        private static void validEdiDate(String ediDate, String signData) {
            if (Strings.isNotEmpty(signData)) {
                if (Strings.isEmpty(ediDate)) {
                    throw new IllegalArgumentException("ediDate is not empty");
                }

                try {
                    // If ediDate is not in the correct ISO 8601 format, this will throw a DateTimeParseException
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                    LocalDateTime.parse(ediDate, formatter);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("ediDate is not ISO8601 format", e);
                }
            }
        }

        private static void validMallReserved(String mallReserved) {
            // 가맹점 예약 필드 선택, 500자 이하 (JSON string format, '"' 사용불가)
            if (mallReserved != null && mallReserved.length() > 500) {
                throw new IllegalArgumentException("mallReserved is 500 characters");
            }

            if (mallReserved != null && mallReserved.contains("\"")) { // PG 제약 조건
                throw new IllegalArgumentException("mallReserved is not contains '\"'");
            }
        }

        private static void validBuyerTel(String buyerTel) {
            // 구매자 전화번호 선택, 20자 이하 ('-' 제외)
            if (buyerTel != null && buyerTel.length() > 20) {
                throw new IllegalArgumentException("buyerTel is 20 characters");
            }

            if (buyerTel != null && buyerTel.contains("-")) { // PG 제약 조건
                throw new IllegalArgumentException("buyerTel is not contains '-'");
            }
        }

        private static void validBuyerEmail(String buyerEmail) {
            // 구매자 이메일 선택, 60자 이하
            if (buyerEmail != null && buyerEmail.length() > 60) {
                throw new IllegalArgumentException("buyerEmail is 60 characters");
            }
        }

        private static void validBuyerName(String buyerName) {
            // 구매자 이름 선택, 30자 이하
            if (buyerName != null && buyerName.length() > 30) {
                throw new IllegalArgumentException("buyerName is 30 characters");
            }
        }

        private static void validTaxFreeAmt(BigDecimal taxFreeAmt, BigDecimal amount) {
            // 면세공급가액 선택, 결제금액보다 작아야 함, 100억 미만
            if (taxFreeAmt != null) {
                if (taxFreeAmt.compareTo(amount) > 0) {
                    throw new IllegalArgumentException("taxFreeAmt is less than amount");
                }

                if (taxFreeAmt.compareTo(BigDecimal.valueOf(10000000000L)) > 0) {
                    throw new IllegalArgumentException("taxFreeAmt is 100억 미만");
                }
            }
        }

        private static void validCardQuota(int cardQuota) {
            // 할부개월수 필수, 0(일시불) 또는 2 ~ 12 (PG사 가입할 때 설정한 최대 할부개월수), 1입력 시 0으로 처리
            if (cardQuota < 0 || cardQuota > 12) {
                throw new IllegalArgumentException("cardQuota is 0(일시불) 또는 2 ~ 12 (PG사 가입할 때 설정한 최대 할부개월수), 1입력 시 0으로 처리");
            }
        }

        private static void validGoodsName(String goodsName) {
            // 상품명 40자 이하
            if (Strings.isBlank(goodsName)) {
                throw new IllegalArgumentException("goodsName is mandatory");
            }

            if (goodsName.length() > 40) {
                throw new IllegalArgumentException("goodsName is 40 characters");
            }
        }

        private static void validAmount(BigDecimal amount) {
            // amount는 not null 이고 100원 이상 100억 미만
            if (amount == null) {
                throw new IllegalArgumentException("amount is mandatory");
            }

            if (amount.compareTo(BigDecimal.valueOf(100)) < 0) {
                throw new IllegalArgumentException("amount is 100원 이상");
            }

            if (amount.compareTo(BigDecimal.valueOf(10000000000L)) > 0) {
                throw new IllegalArgumentException("amount is 100억 미만");
            }
        }

        private static void validOrderId(Id<String> orderId) {
            // orderId는 not blank 이고 1 ~ 64자리 이내
            if (Strings.isBlank(orderId.getValue())) {
                throw new IllegalArgumentException("orderId is mandatory");
            }

            if (orderId.length() < 1 || orderId.length() > 64) {
                throw new IllegalArgumentException("orderId is 1 ~ 64 characters");
            }
        }


    }
}
