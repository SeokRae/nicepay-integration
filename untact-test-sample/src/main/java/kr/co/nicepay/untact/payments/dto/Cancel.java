package kr.co.nicepay.untact.payments.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Cancel {
    private String tid;
    private int amount;
    private String cancelledAt;
    private String reason;
    private String receiptUrl;
    private int couponAmt;

    @Builder
    public Cancel(String tid, int amount, String cancelledAt, String reason, String receiptUrl, int couponAmt) {
        this.tid = tid;
        this.amount = amount;
        this.cancelledAt = cancelledAt;
        this.reason = reason;
        this.receiptUrl = receiptUrl;
        this.couponAmt = couponAmt;
    }
}
