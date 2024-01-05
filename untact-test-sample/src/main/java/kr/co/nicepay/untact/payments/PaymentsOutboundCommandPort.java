package kr.co.nicepay.untact.payments;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.payments.dto.PaymentsApproveRequest;
import kr.co.nicepay.untact.payments.dto.PaymentsCancelRequest;
import kr.co.nicepay.untact.payments.dto.PaymentsNetCancelRequest;
import kr.co.nicepay.untact.payments.dto.PaymentsResponse;
import org.springframework.data.util.Pair;

public interface PaymentsOutboundCommandPort {
    Pair<Integer, PaymentsResponse> payments(Id<String> tid, PaymentsApproveRequest paymentsApproveRequest);

    Pair<Integer, PaymentsResponse> cancel(Id<String> tid, PaymentsCancelRequest cancelReq);

    Pair<Integer, PaymentsResponse> netCancel(PaymentsNetCancelRequest netCancelReq);

}
