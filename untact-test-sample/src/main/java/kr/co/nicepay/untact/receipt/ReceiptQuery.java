package kr.co.nicepay.untact.receipt;


import kr.co.nicepay.untact.common.domain.Id;
import kr.co.nicepay.untact.receipt.dto.ReceiptRetrieveReq;
import kr.co.nicepay.untact.receipt.dto.ReceiptRetrieveRes;
import org.springframework.data.util.Pair;

public interface ReceiptQuery {
    Pair<Integer, ReceiptRetrieveRes> retrieve(Id<String> tid, ReceiptRetrieveReq receiptSearchReq);
}
