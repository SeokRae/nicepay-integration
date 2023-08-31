package kr.co.nicepay.untact.checkamt;


import kr.co.nicepay.untact.checkamt.dto.CheckAmountReq;
import kr.co.nicepay.untact.checkamt.dto.CheckAmountRes;
import kr.co.nicepay.untact.common.domain.Id;
import org.springframework.data.util.Pair;

public interface CheckAmountQuery {

    Pair<Integer, CheckAmountRes> checkAmount(Id<String> tid, CheckAmountReq checkAmountReq);
}
