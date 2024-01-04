package kr.co.nicepay.untact.subscribe;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.payments.dto.PaymentsRes;
import kr.co.nicepay.untact.subscribe.dto.*;

public interface SubscribeOutboundPort {

    SubscribeCreatePgRes issue(SubscribeCreatePgReq subscribeCreatePgReq);

    PaymentsRes payments(Id<String> bid, SubscribePaymentsReq subscribePaymentsReq);

    SubscribeExpireRes expire(Id<String> bid, SubscribeExpireReq subscribeExpireReq);
}
