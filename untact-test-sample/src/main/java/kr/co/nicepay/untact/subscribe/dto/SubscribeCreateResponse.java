package kr.co.nicepay.untact.subscribe.dto;


import io.micrometer.common.util.StringUtils;
import kr.co.nicepay.untact.common.domain.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class SubscribeCreateResponse {

    // Mandatory
    private String resultCode;
    private String resultMsg;
    private Id<String> orderId;
    private Id<String> tid;
    // Condition
    private Id<String> bid;
    private String authDate;
    private String cardCode;
    private String cardName;

    @ConstructorProperties({"resultCode", "resultMsg", "orderId", "tid", "bid", "authDate", "cardCode", "cardName"})
    public SubscribeCreateResponse(String resultCode, String resultMsg, Id<String> orderId, Id<String> tid, Id<String> bid, String authDate, String cardCode, String cardName) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.orderId = orderId;
        this.tid = tid;
        this.bid = bid;
        this.authDate = authDate;
        this.cardCode = cardCode;
        this.cardName = StringUtils.isNotBlank(cardName) ? cardName.replace("[", "").replace("]", "") : null;
    }
}
