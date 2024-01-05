package kr.co.nicepay.untact.subscribe;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.payments.dto.PaymentsResponse;
import kr.co.nicepay.untact.subscribe.dto.*;

public interface SubscribeOutboundPort {

    SubscribeCreateResponse issue(SubscribeCreateRequest subscribeCreateRequest);

    PaymentsResponse payments(Id<String> bid, SubscribePaymentsRequest subscribePaymentsRequest);

    SubscribeExpireResponse expire(Id<String> bid, SubscribeExpireRequest subscribeExpireRequest);
}
