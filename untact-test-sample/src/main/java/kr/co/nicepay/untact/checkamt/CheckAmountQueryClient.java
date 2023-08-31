package kr.co.nicepay.untact.checkamt;


import kr.co.nicepay.untact.checkamt.dto.CheckAmountReq;
import kr.co.nicepay.untact.checkamt.dto.CheckAmountRes;
import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.common.helper.JsonHelper;
import kr.co.nicepay.untact.common.helper.RestTemplateHelper;
import kr.co.nicepay.untact.core.props.hosts.DomainUrlProperties;
import kr.co.nicepay.untact.core.props.merchant.MerchantProperties;
import kr.co.nicepay.untact.core.props.urls.CheckAmountUrlProperties;
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
public class CheckAmountQueryClient implements CheckAmountQuery {

    private final DomainUrlProperties domainUrlProperties;
    private final CheckAmountUrlProperties checkAmountUrlProperties;
    private final MerchantProperties merchantProperties;
    private final RestTemplateHelper restTemplate;
    private final JsonHelper mapper;

    @Override
    public Pair<Integer, CheckAmountRes> checkAmount(
            final Id<String> tid,
            final CheckAmountReq checkAmountReq
    ) {
        log.info("tid: {}, checkAmountReq: {}", tid, checkAmountReq);

        Map<String, Object> pathVariables = new HashMap<>();
        pathVariables.put("tid", tid.getValue());

        ResponseEntity<CheckAmountRes> checkAmountResponse = restTemplate.postForEntity(
                domainUrlProperties.getApi() + checkAmountUrlProperties.getCheckAmount(),
                pathVariables,
                merchantProperties.getToken(),
                mapper.toJson(checkAmountReq),
                CheckAmountRes.class
        );

        if (!checkAmountResponse.getStatusCode().is2xxSuccessful()) {
            log.error("CheckAmountRestTemplateUseCase.checkAmount: {}", checkAmountResponse);
            throw new RuntimeException("승인 금액검증 실패");
        }

        return Pair.of(
                checkAmountResponse.getStatusCode().value(),
                checkAmountResponse.getBody()
        );
    }
}
