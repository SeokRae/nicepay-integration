package kr.co.nicepay.untact.receipt;

import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.common.helper.JsonHelper;
import kr.co.nicepay.untact.common.helper.RestTemplateHelper;
import kr.co.nicepay.untact.core.props.hosts.DomainUrlProperties;
import kr.co.nicepay.untact.core.props.merchant.MerchantProperties;
import kr.co.nicepay.untact.core.props.urls.ReceiptUrlProperties;
import kr.co.nicepay.untact.receipt.dto.ReceiptCancelReq;
import kr.co.nicepay.untact.receipt.dto.ReceiptCancelRes;
import kr.co.nicepay.untact.receipt.dto.ReceiptCreateReq;
import kr.co.nicepay.untact.receipt.dto.ReceiptCreateRes;
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
public class ReceiptCommandClient implements ReceiptCommand {

    private final DomainUrlProperties domainUrlProperties;
    private final ReceiptUrlProperties receiptUrlProperties;
    private final MerchantProperties merchantProperties;
    private final RestTemplateHelper restTemplate;
    private final JsonHelper mapper;

    @Override
    public Pair<Integer, ReceiptCreateRes> issue(
            final ReceiptCreateReq receiptCreateReq
    ) {
        log.info("ReceiptRestTempleUseCase.issue : {}", receiptCreateReq);

        ResponseEntity<ReceiptCreateRes> issueReceipt = restTemplate.postRestTemplate(
                domainUrlProperties.getApi() + receiptUrlProperties.getIssue(),
                merchantProperties.getToken(),
                mapper.toJson(receiptCreateReq),
                ReceiptCreateRes.class
        );

        if (!issueReceipt.getStatusCode().is2xxSuccessful()) {
            log.error("ReceiptRestTempleClient.issue: {}", issueReceipt);
            throw new RuntimeException("영수증 발급 실패");
        }

        return Pair.of(
                issueReceipt.getStatusCode().value(),
                issueReceipt.getBody()
        );
    }

    @Override
    public Pair<Integer, ReceiptCancelRes> cancel(
            final Id<String> tid, final ReceiptCancelReq receiptCancelReq
    ) {
        log.info("ReceiptRestTempleUseCase.cancel : {}", receiptCancelReq);


        Map<String, Object> pathVariable = new HashMap<>();
        pathVariable.put("tid", tid.getValue());

        ResponseEntity<ReceiptCancelRes> cancelReceipt = restTemplate.postForEntity(
                domainUrlProperties.getApi() + receiptUrlProperties.getCancel(),
                pathVariable,
                merchantProperties.getToken(),
                mapper.toJson(receiptCancelReq),
                ReceiptCancelRes.class
        );

        if (!cancelReceipt.getStatusCode().is2xxSuccessful()) {
            log.error("ReceiptRestTempleClient.cancel: {}", cancelReceipt);
            throw new RuntimeException("영수증 취소 실패");
        }

        return Pair.of(
                cancelReceipt.getStatusCode().value(),
                cancelReceipt.getBody()
        );
    }
}
