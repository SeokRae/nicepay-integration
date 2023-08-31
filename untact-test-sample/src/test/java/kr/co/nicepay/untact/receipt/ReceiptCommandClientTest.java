package kr.co.nicepay.untact.receipt;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.common.types.PaymentMethod;
import kr.co.nicepay.untact.common.types.PaymentStatus;
import kr.co.nicepay.untact.common.types.ReceiptType;
import kr.co.nicepay.untact.core.CardInfTest;
import kr.co.nicepay.untact.receipt.dto.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest
@DisplayName("현금영수증 시나리오 테스트")
@TestInstance(PER_CLASS)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ReceiptCommandClientTest extends CardInfTest {

    private static final Logger log = LoggerFactory.getLogger(ReceiptCommandClientTest.class);
    private static Id<String> issueReceiptTid;
    @Autowired
    private ReceiptCommandClient receiptCommandClient;
    @Autowired
    private ReceiptQueryClient receiptQueryClient;

    @Order(1)
    @DisplayName("현금영수증 발급 테스트")
    @Test
    void issueReceiptTest() {

        // given
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");

        ReceiptCreateReq receiptCreateReq = ReceiptCreateReq.builder()
                .orderId(orderId)
                .amount(BigDecimal.valueOf(1004))
                .goodsName("현금 영수증 발급 테스트")
                .receiptType(ReceiptType.INDIVIDUAL)
                .receiptNo("01000000000")
                .supplyAmt(904)
                .goodsVat(100)
                .taxFreeAmt(0)
                .serviceAmt(0)
                .build();

        // when
        Pair<Integer, ReceiptCreateRes> issueReceiptResponse = receiptCommandClient.issue(receiptCreateReq);
        log.info("{}", issueReceiptResponse);

        assertThat(issueReceiptResponse.getFirst()).isEqualTo(HttpStatus.OK.value());

        ReceiptCreateRes issueReceipt = issueReceiptResponse.getSecond();

        assertThat(issueReceipt.getResultCode()).isEqualTo("0000");
        assertThat(issueReceipt.getResultMsg()).contains("현금영수증 처리 성공");

        issueReceiptTid = issueReceipt.getTid();
    }

    @Order(2)
    @DisplayName("현금 영수증 발급 상태 조회 테스트")
    @Test
    void retrieveReceiptTest() {

        ReceiptRetrieveReq receiptRetrieveReq = ReceiptRetrieveReq.builder()
                .returnCharSet(StandardCharsets.UTF_8.name())
                .build();

        Pair<Integer, ReceiptRetrieveRes> retrieveReceiptResponse = receiptQueryClient.retrieve(
                issueReceiptTid, receiptRetrieveReq
        );

        log.info("{}", retrieveReceiptResponse);

        assertThat(retrieveReceiptResponse.getFirst()).isEqualTo(HttpStatus.OK.value());

        ReceiptRetrieveRes retrieveReceipt = retrieveReceiptResponse.getSecond();

        assertThat(retrieveReceipt.getResultCode()).isEqualTo("0000");
        assertThat(retrieveReceipt.getResultMsg()).isEqualTo("정상 처리되었습니다.");
        assertThat(retrieveReceipt.getPayMethod()).isEqualTo(PaymentMethod.CASH_RECEIPT);
        assertThat(retrieveReceipt.getStatus()).isEqualTo(PaymentStatus.ISSUE_REQUESTED);
    }

    @Order(3)
    @DisplayName("현금 영수증 발급 취소 테스트")
    @Test
    void cancelReceiptTest() {
        // given
        ReceiptCancelReq receiptCancelReq = ReceiptCancelReq.builder()
                .orderId(UUID.randomUUID().toString())
                .reason("현금 영수증 취소 테스트")
                .build();

        // when
        Pair<Integer, ReceiptCancelRes> cancelReceiptResponse = receiptCommandClient.cancel(
                issueReceiptTid, receiptCancelReq
        );

        log.info("{}", cancelReceiptResponse);

        // then
        assertThat(cancelReceiptResponse.getFirst()).isEqualTo(HttpStatus.OK.value());

        ReceiptCancelRes cancelReceipt = cancelReceiptResponse.getSecond();

        assertThat(cancelReceipt.getResultCode()).isEqualTo("0000");
        assertThat(cancelReceipt.getResultMsg()).contains("취소 성공");
    }

    @Order(4)
    @DisplayName("현금 영수증 발급 취소 상태 조회 테스트")
    @Test
    void retrieveReceiptWithCancelledTest() {
        ReceiptRetrieveReq receiptRetrieveReq = ReceiptRetrieveReq.builder()
                .returnCharSet(StandardCharsets.UTF_8.name())
                .build();

        Pair<Integer, ReceiptRetrieveRes> retrieveReceiptResponse = receiptQueryClient.retrieve(
                issueReceiptTid, receiptRetrieveReq
        );

        log.info("{}", retrieveReceiptResponse);

        assertThat(retrieveReceiptResponse.getFirst()).isEqualTo(HttpStatus.OK.value());

        ReceiptRetrieveRes retrieveReceipt = retrieveReceiptResponse.getSecond();

        assertThat(retrieveReceipt.getResultCode()).isEqualTo("0000");
        assertThat(retrieveReceipt.getResultMsg()).contains("정상 처리되었습니다.");
        assertThat(retrieveReceipt.getPayMethod()).isEqualTo(PaymentMethod.CASH_RECEIPT);
    }
}