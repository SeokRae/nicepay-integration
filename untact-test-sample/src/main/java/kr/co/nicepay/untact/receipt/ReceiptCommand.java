package kr.co.nicepay.untact.receipt;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.receipt.dto.ReceiptCancelReq;
import kr.co.nicepay.untact.receipt.dto.ReceiptCancelRes;
import kr.co.nicepay.untact.receipt.dto.ReceiptCreateReq;
import kr.co.nicepay.untact.receipt.dto.ReceiptCreateRes;
import org.springframework.data.util.Pair;

public interface ReceiptCommand {

    Pair<Integer, ReceiptCreateRes> issue(ReceiptCreateReq receiptCreateReq);

    Pair<Integer, ReceiptCancelRes> cancel(Id<String> tid, ReceiptCancelReq receiptCancelReq);
}
