package kr.co.nicepay.untact.terms;

import kr.co.nicepay.untact.common.helper.RestTemplateHelper;
import kr.co.nicepay.untact.core.props.hosts.DomainUrlProperties;
import kr.co.nicepay.untact.core.props.merchant.MerchantProperties;
import kr.co.nicepay.untact.core.props.urls.TermsUrlProperties;
import kr.co.nicepay.untact.terms.dto.event.CardEventReq;
import kr.co.nicepay.untact.terms.dto.event.CardEventRes;
import kr.co.nicepay.untact.terms.dto.interestfree.CardInterestFeeReq;
import kr.co.nicepay.untact.terms.dto.interestfree.CardInterestFreeRes;
import kr.co.nicepay.untact.terms.dto.term.TermsReq;
import kr.co.nicepay.untact.terms.dto.term.TermsRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TermsQueryClient implements TermsQuery {

    private final DomainUrlProperties domainUrlProperties;
    private final TermsUrlProperties termsUrlProperties;
    private final MerchantProperties merchantProperties;
    private final RestTemplateHelper restTemplateHelper;

    @Override
    public Pair<Integer, TermsRes> terms(
            final TermsReq termsReq
    ) {
        log.info("{}", termsReq);

        ResponseEntity<TermsRes> termsResponse = restTemplateHelper.getParamsRestTemplate(
                domainUrlProperties.getApi() + termsUrlProperties.getTerms(),
                merchantProperties.getToken(),
                termsReq.toMap(),
                TermsRes.class
        );

        if (!termsResponse.getStatusCode().is2xxSuccessful()) {
            log.error("TermsQueryClient.terms: {}", termsResponse);
            throw new RuntimeException("약관 조회 실패");
        }

        return Pair.of(
                termsResponse.getStatusCode().value(),
                termsResponse.getBody()
        );
    }

    @Override
    public Pair<Integer, CardEventRes> cardEvent(
            final CardEventReq cardEventReq
    ) {
        log.info("{}", cardEventReq);

        ResponseEntity<CardEventRes> cardEventResponse = restTemplateHelper.getParamsRestTemplate(
                domainUrlProperties.getApi() + termsUrlProperties.getCardEvent(),
                merchantProperties.getToken(),
                cardEventReq.toMap(),
                CardEventRes.class
        );

        if (!cardEventResponse.getStatusCode().is2xxSuccessful()) {
            log.error("TermsQueryClient.cardEvent: {}", cardEventResponse);
            throw new RuntimeException("카드 이벤트 조회 실패");
        }

        return Pair.of(
                cardEventResponse.getStatusCode().value(),
                cardEventResponse.getBody()
        );
    }

    @Override
    public Pair<Integer, CardInterestFreeRes> cardInterestFree(
            final CardInterestFeeReq cardInterestFeeReq
    ) {
        log.info("{}", cardInterestFeeReq);

        ResponseEntity<CardInterestFreeRes> cardInterestFreeResponse = restTemplateHelper.getParamsRestTemplate(
                domainUrlProperties.getApi() + termsUrlProperties.getCardInterestFree(),
                merchantProperties.getToken(),
                cardInterestFeeReq.toMap(),
                CardInterestFreeRes.class
        );

        if (!cardInterestFreeResponse.getStatusCode().is2xxSuccessful()) {
            log.error("TermsQueryClient.cardInterestFree: {}", cardInterestFreeResponse);
            throw new RuntimeException("카드 무이자 조회 실패");
        }

        return Pair.of(
                cardInterestFreeResponse.getStatusCode().value(),
                cardInterestFreeResponse.getBody()
        );
    }
}
