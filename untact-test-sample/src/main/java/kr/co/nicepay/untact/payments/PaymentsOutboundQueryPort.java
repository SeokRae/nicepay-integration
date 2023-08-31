package kr.co.nicepay.untact.payments;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.payments.dto.PaymentsRes;
import org.springframework.data.util.Pair;

public interface PaymentsOutboundQueryPort {

    Pair<Integer, PaymentsRes> retrieve(Id<String> tid);

    Pair<Integer, PaymentsRes> retrieveOrderId(Id<String> orderId);

}
