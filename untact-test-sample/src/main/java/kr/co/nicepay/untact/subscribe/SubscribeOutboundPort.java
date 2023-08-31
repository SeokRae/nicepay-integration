package kr.co.nicepay.untact.subscribe;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.payments.dto.PaymentsRes;
import kr.co.nicepay.untact.subscribe.dto.*;
import org.springframework.data.util.Pair;

public interface SubscribeOutboundPort {

    Pair<Integer, SubscribeCreatePgRes> issue(SubscribeCreatePgReq subscribeCreatePgReq);

    Pair<Integer, PaymentsRes> payments(Id<String> bid, SubscribePaymentsReq subscribePaymentsReq);

    Pair<Integer, SubscribeExpireRes> expire(Id<String> bid, SubscribeExpireReq subscribeExpireReq);
}
