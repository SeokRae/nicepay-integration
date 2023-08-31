package kr.co.nicepay.untact.subscribe;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.common.helper.JsonHelper;
import kr.co.nicepay.untact.common.helper.RestTemplateHelper;
import kr.co.nicepay.untact.core.props.hosts.DomainUrlProperties;
import kr.co.nicepay.untact.core.props.merchant.MerchantProperties;
import kr.co.nicepay.untact.core.props.urls.SubscribeUrlProperties;
import kr.co.nicepay.untact.payments.dto.PaymentsRes;
import kr.co.nicepay.untact.subscribe.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscribeCommandClient implements SubscribeOutboundPort {

    private final DomainUrlProperties domainUrlProperties;
    // 정기결제 설정 정보
    private final SubscribeUrlProperties subscribeUrlProperties;
    private final MerchantProperties merchantProperties;
    // 공통
    private final RestTemplateHelper restTemplate;
    private final JsonHelper mapper;

    @Override
    public Pair<Integer, SubscribeCreatePgRes> issue(
            final SubscribeCreatePgReq subscribeCreatePgReq
    ) {

        log.info("SubscribeRestTempleUseCase.issue : {}", subscribeCreatePgReq);

        ResponseEntity<SubscribeCreatePgRes> issuedSubscribeKey = restTemplate.postRestTemplate(
                domainUrlProperties.getApi() + subscribeUrlProperties.getIssue(),
                merchantProperties.getToken(),
                mapper.toJson(subscribeCreatePgReq),
                SubscribeCreatePgRes.class
        );

        if (!issuedSubscribeKey.getStatusCode().is2xxSuccessful()) {
            log.error("SubscribeRestTempleUseCase.issue: {}", issuedSubscribeKey);
            throw new RuntimeException("정기결제 빌키발급 실패");
        }

        return Pair.of(
                issuedSubscribeKey.getStatusCode().value(),
                issuedSubscribeKey.getBody()
        );
    }

    @Override
    public Pair<Integer, PaymentsRes> payments(
            final Id<String> bid,
            final SubscribePaymentsReq subscribePaymentsReq
    ) {

        log.info("SubscribeRestTempleUseCase.payments");
        ResponseEntity<PaymentsRes> postSubsPayments;
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("bid", bid.getValue());

        postSubsPayments = restTemplate.postForEntity(
                domainUrlProperties.getApi() + subscribeUrlProperties.getPayments(),
                uriVariables,
                merchantProperties.getToken(),
                mapper.toJson(subscribePaymentsReq),
                PaymentsRes.class
        );

        if (!postSubsPayments.getStatusCode().is2xxSuccessful()) {
            log.info("SubscribeRestTempleUseCase.payments: {}", postSubsPayments);
            throw new RuntimeException("정기결제 결제 요청 실패");
        }

        // 빌키 결제 요청에 대한 비즈니스 응답 코드는 별도 처리

        return Pair.of(
                postSubsPayments.getStatusCode().value(),
                postSubsPayments.getBody()
        );
    }

    @Override
    public Pair<Integer, SubscribeExpireRes> expire(
            final Id<String> bid,
            final SubscribeExpireReq subscribeExpireReq
    ) {

        log.info("SubscribeRestTempleUseCase.expire");

        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("bid", bid.getValue());

        ResponseEntity<SubscribeExpireRes> postSubsExpire = restTemplate.postForEntity(
                domainUrlProperties.getApi() + subscribeUrlProperties.getExpire(),
                uriVariables,
                merchantProperties.getToken(),
                mapper.toJson(subscribeExpireReq),
                SubscribeExpireRes.class
        );

        if (!postSubsExpire.getStatusCode().is2xxSuccessful()) {
            log.error("SubscribeRestTempleUseCase.expire: {}", postSubsExpire);
            throw new RuntimeException("정기결제 만료 요청 실패");
        }

        // 빌키 만료 요청에 대한 비즈니스 응답 코드는 별도 처리

        return Pair.of(
                postSubsExpire.getStatusCode().value(),
                postSubsExpire.getBody()
        );
    }
}
