package kr.co.nicepay.untact.payments;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.payments.dto.PaymentsResponse;
import org.springframework.data.util.Pair;

public interface PaymentsOutboundQueryPort {

    Pair<Integer, PaymentsResponse> retrieve(Id<String> tid);

    Pair<Integer, PaymentsResponse> retrieveOrderId(Id<String> orderId);

}
