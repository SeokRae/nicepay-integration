package kr.co.nicepay.untact.terms;


import kr.co.nicepay.untact.terms.dto.event.CardEventReq;
import kr.co.nicepay.untact.terms.dto.event.CardEventRes;
import kr.co.nicepay.untact.terms.dto.interestfree.CardInterestFeeReq;
import kr.co.nicepay.untact.terms.dto.interestfree.CardInterestFreeRes;
import kr.co.nicepay.untact.terms.dto.term.TermsReq;
import kr.co.nicepay.untact.terms.dto.term.TermsRes;
import org.springframework.data.util.Pair;

public interface TermsQuery {
    Pair<Integer, TermsRes> terms(TermsReq termsReq);

    Pair<Integer, CardEventRes> cardEvent(CardEventReq cardEventReq);

    Pair<Integer, CardInterestFreeRes> cardInterestFree(CardInterestFeeReq cardInterestFeeReq);
}
