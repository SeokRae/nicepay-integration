package kr.co.nicepay.untact.payments;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.payments.dto.PaymentsApproveReq;
import kr.co.nicepay.untact.payments.dto.PaymentsCancelReq;
import kr.co.nicepay.untact.payments.dto.PaymentsNetCancelReq;
import kr.co.nicepay.untact.payments.dto.PaymentsRes;
import org.springframework.data.util.Pair;

public interface PaymentsOutboundCommandPort {
    Pair<Integer, PaymentsRes> payments(Id<String> tid, PaymentsApproveReq paymentsApproveReq);

    Pair<Integer, PaymentsRes> cancel(Id<String> tid, PaymentsCancelReq cancelReq);

    Pair<Integer, PaymentsRes> netCancel(PaymentsNetCancelReq netCancelReq);

}
