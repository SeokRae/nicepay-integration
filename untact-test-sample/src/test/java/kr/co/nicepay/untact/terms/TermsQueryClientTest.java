package kr.co.nicepay.untact.terms;


import kr.co.nicepay.untact.core.CardInfTest;
import kr.co.nicepay.untact.terms.dto.event.CardEventReq;
import kr.co.nicepay.untact.terms.dto.event.CardEventRes;
import kr.co.nicepay.untact.terms.dto.interestfree.CardInterestFeeReq;
import kr.co.nicepay.untact.terms.dto.interestfree.CardInterestFreeRes;
import kr.co.nicepay.untact.terms.dto.term.TermsReq;
import kr.co.nicepay.untact.terms.dto.term.TermsRes;
import kr.co.nicepay.untact.common.types.TermsType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static kr.co.nicepay.untact.common.utils.SignDataEncrypt.encryptSHA256;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayName("약관조회 시나리오 테스트")
class TermsQueryClientTest extends CardInfTest {

    private static final Logger log = LoggerFactory.getLogger(TermsQueryClientTest.class);

    @Autowired
    private TermsQueryClient termsQueryClient;

    private static Stream<Arguments> terms() {
        return Stream.of(
                Arguments.of(TermsType.ELECTRONIC_FINANCIAL_TRANSACTIONS),
                Arguments.of(TermsType.TELECOMMUNICATION_CHARGING),
                Arguments.of(TermsType.COLLECT_PERSONAL_INFO),
                Arguments.of(TermsType.SHARING_PERSONAL_INFORMATION)
        );
    }

    @DisplayName("약관조회 테스트")
    @MethodSource(value = "terms")
    @ParameterizedTest(name = "{index} {displayName} - {0}")
    void retrieveTermsTest(TermsType termsType) {
        // given
        TermsReq termsReq = TermsReq.builder()
                .termsType(termsType)
                .build();

        // when
        Pair<Integer, TermsRes> termsResponse = termsQueryClient.terms(termsReq);

        // then
        log.info("termsResponse = {}", termsResponse);

        assertThat(termsResponse.getFirst()).isEqualTo(200);

        TermsRes terms = termsResponse.getSecond();

        assertThat(terms.getResultCode()).isEqualTo("0000");
        assertThat(terms.getResultMsg()).isEqualTo("정상 처리되었습니다.");
        assertThat(terms.getTermsTitle()).isEqualTo(termsType.getTitle());
    }

    @DisplayName("카드이벤트조회 테스트")
    @Test
    void retrieveCardEventTest() {
        // given
        String ediDate = LocalDateTime.now().toString();
        String signData = encryptSHA256(ediDate + merchantProperties.getSecretKey());

        CardEventReq cardEventReq = CardEventReq.builder()
                .amount(BigDecimal.valueOf(50000))
                .useAuth(true)
                .signData(signData)
                .ediDate(ediDate)
                .build();

        // when
        Pair<Integer, CardEventRes> cardEventResponse = termsQueryClient.cardEvent(cardEventReq);

        // then
        log.info("cardEventResponse = {}", cardEventResponse);

        assertThat(cardEventResponse.getFirst()).isEqualTo(200);

        CardEventRes cardEvent = cardEventResponse.getSecond();

        assertThat(cardEvent.getResultCode()).isEqualTo("0000");
        assertThat(cardEvent.getResultMsg()).isEqualTo("정상 처리되었습니다.");
        assertThat(cardEvent.getSignature()).isEqualTo(encryptSHA256(cardEvent.getEdiDate() + merchantProperties.getSecretKey()));
    }

    @DisplayName("카드 무이자 조회 테스트")
    @Test
    void retrieveCardInterestTest() {
        // given
        String ediDate = LocalDateTime.now().toString();
        String signData = encryptSHA256(ediDate + merchantProperties.getSecretKey());

        CardInterestFeeReq cardInterestFeeReq = CardInterestFeeReq.builder()
                .useAuth(true)
                .signData(signData)
                .ediDate(ediDate)
                .build();

        // when
        Pair<Integer, CardInterestFreeRes> cardInterestFeeResponse = termsQueryClient.cardInterestFree(cardInterestFeeReq);

        // then
        log.info("cardInterestFeeResponse = {}", cardInterestFeeResponse);

        assertThat(cardInterestFeeResponse.getFirst()).isEqualTo(200);

        CardInterestFreeRes cardInterestFree = cardInterestFeeResponse.getSecond();

        assertThat(cardInterestFree.getResultCode()).isEqualTo("0000");
        assertThat(cardInterestFree.getResultMsg()).isEqualTo("정상 처리되었습니다.");

    }
}