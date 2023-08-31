package kr.co.nicepay.untact.receipt;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.common.helper.RestTemplateHelper;
import kr.co.nicepay.untact.core.props.hosts.DomainUrlProperties;
import kr.co.nicepay.untact.core.props.merchant.MerchantProperties;
import kr.co.nicepay.untact.core.props.urls.ReceiptUrlProperties;
import kr.co.nicepay.untact.receipt.dto.ReceiptRetrieveReq;
import kr.co.nicepay.untact.receipt.dto.ReceiptRetrieveRes;
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
public class ReceiptQueryClient implements ReceiptQuery {

    private final DomainUrlProperties domainUrlProperties;
    private final ReceiptUrlProperties receiptUrlProperties;
    private final MerchantProperties merchantProperties;
    private final RestTemplateHelper restTemplate;

    @Override
    public Pair<Integer, ReceiptRetrieveRes> retrieve(
            final Id<String> tid, final ReceiptRetrieveReq receiptRetrieveReq
    ) {
        log.info("ReceiptRestTempleUseCase.retrieve : {}", receiptRetrieveReq);

        Map<String, String> pathVariable = new HashMap<>();
        pathVariable.put("tid", tid.getValue());

        ResponseEntity<ReceiptRetrieveRes> retrieveReceipt = restTemplate.getForEntity(
                domainUrlProperties.getApi() + receiptUrlProperties.getRetrieve(),
                pathVariable,
                merchantProperties.getToken(),
                ReceiptRetrieveRes.class
        );

        if (!retrieveReceipt.getStatusCode().is2xxSuccessful()) {
            log.error("ReceiptRestTempleClient.retrieve: {}", retrieveReceipt);
            throw new RuntimeException("영수증 조회 실패");
        }

        return Pair.of(
                retrieveReceipt.getStatusCode().value(),
                retrieveReceipt.getBody()
        );
    }

}
