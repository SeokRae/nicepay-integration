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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

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
    public SubscribeCreatePgRes issue(
            final SubscribeCreatePgReq subscribeCreatePgReq
    ) {

        log.info("SubscribeRestTempleUseCase.issue : {}", subscribeCreatePgReq);

        try {
            ResponseEntity<SubscribeCreatePgRes> issuedSubscribeKey = subscribeHttpRequest(
                    subscribeUrlProperties.getIssue()
                    , new HashMap<>()
                    , subscribeCreatePgReq
                    , SubscribeCreatePgRes.class
            );
            return issuedSubscribeKey.getBody();

        } catch (HttpClientErrorException e) {
            log.error("Subscribe Rest OutBound Client Exception issue: {}", e.getMessage());
            throw new HttpClientErrorException(e.getStatusCode(), "Client Exception");
        } catch (HttpServerErrorException e) {
            log.error("Subscribe Rest OutBound Server Exception issue: {}", e.getMessage());
            throw new HttpServerErrorException(e.getStatusCode(), "Server Exception");
        } catch (ResourceAccessException e) {
            log.error("Subscribe Rest OutBound Resource Access Exception issue: {}", e.getMessage());
            throw new ResourceAccessException("Subscribe Rest OutBound Resource Access Exception");
        } catch (RuntimeException e) {
            log.error("Subscribe Rest OutBound Runtime Exception issue: {}", e.getMessage());
            throw new RuntimeException("Subscribe Rest OutBound Runtime Exception");
        } catch (Exception e) {
            log.error("Subscribe Rest OutBound Exception issue: {}", e.getMessage());
            throw new RuntimeException("Subscribe Rest OutBound Exception");
        }
    }

    @Override
    public PaymentsRes payments(
            final Id<String> bid,
            final SubscribePaymentsReq subscribePaymentsReq
    ) {
        log.info("SubscribeRestTempleUseCase.processPayment");

        Map<String, Object> uriVariables = createUriVariables(bid);

        try {
            ResponseEntity<PaymentsRes> response = subscribeHttpRequest(
                    subscribeUrlProperties.getPayments()
                    , uriVariables
                    , subscribePaymentsReq
                    , PaymentsRes.class);

            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Subscribe Rest OutBound Client Exception payments: {}", e.getMessage());
            throw new HttpClientErrorException(e.getStatusCode(), "Client Exception");
        } catch (HttpServerErrorException e) {
            log.error("Subscribe Rest OutBound Server Exception payments: {}", e.getMessage());
            throw new HttpServerErrorException(e.getStatusCode(), "Server Exception");
        } catch (ResourceAccessException e) {
            log.error("Subscribe Rest OutBound Resource Access Exception payments: {}", e.getMessage());
            throw new ResourceAccessException("Subscribe Rest OutBound Resource Access Exception");
        } catch (RuntimeException e) {
            log.error("Subscribe Rest OutBound Runtime Exception payments: {}", e.getMessage());
            throw new RuntimeException("Subscribe Rest OutBound Runtime Exception");
        } catch (Exception e) {
            log.error("Subscribe Rest OutBound Exception payments: {}", e.getMessage());
            throw new RuntimeException("Subscribe Rest OutBound Exception");
        }
    }

    @Override
    public SubscribeExpireRes expire(
            final Id<String> bid,
            final SubscribeExpireReq subscribeExpireReq
    ) {

        log.info("SubscribeRestTempleUseCase.expire");

        Map<String, Object> uriVariables = createUriVariables(bid);

        try {
            ResponseEntity<SubscribeExpireRes> postSubsExpire = restTemplate.postForEntity(
                    domainUrlProperties.getApi() + subscribeUrlProperties.getExpire(),
                    uriVariables,
                    merchantProperties.getToken(),
                    mapper.toJson(subscribeExpireReq),
                    SubscribeExpireRes.class
            );

            return postSubsExpire.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Subscribe Rest OutBound Client Exception expire: {}", e.getMessage());
            throw new HttpClientErrorException(e.getStatusCode(), "Client Exception");
        } catch (HttpServerErrorException e) {
            log.error("Subscribe Rest OutBound Server Exception expire: {}", e.getMessage());
            throw new HttpServerErrorException(e.getStatusCode(), "Server Exception");
        } catch (ResourceAccessException e) {
            log.error("Subscribe Rest OutBound Resource Access Exception expire: {}", e.getMessage());
            throw new ResourceAccessException("Subscribe Rest OutBound Resource Access Exception");
        } catch (RuntimeException e) {
            log.error("Subscribe Rest OutBound Runtime Exception expire: {}", e.getMessage());
            throw new RuntimeException("Subscribe Rest OutBound Runtime Exception");
        } catch (Exception e) {
            log.error("Subscribe Rest OutBound Exception expire: {}", e.getMessage());
            throw new RuntimeException("Subscribe Rest OutBound Exception");
        }
    }

    private Map<String, Object> createUriVariables(Id<String> bid) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("bid", bid.getValue());
        return uriVariables;
    }

    private <T, R> ResponseEntity<R> subscribeHttpRequest(String payments, Map<String, Object> uriVariables, T request, Class<R> responseType) {
        return restTemplate.postForEntity(
                domainUrlProperties.getApi() + payments,
                uriVariables,
                merchantProperties.getToken(),
                mapper.toJson(request),
                responseType
        );
    }
}
