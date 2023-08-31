package kr.co.nicepay.untact.receipt.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptCancel {
    private String tid;
    private long amount;
    private String status;
    private String cancelledAt;
    private String receiptType;
    private String approveNo;
    private long supplyAmt;
    private long goodsVat;
    private long taxFreeAmt;
    private long serviceAmt;
    private String receiptUrl;
}
