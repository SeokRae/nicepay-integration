package kr.co.nicepay.untact.receipt.dto;


import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptCancelResponse {
    private String resultCode;
    private String resultMsg;
    private String tid;

    private String orderId;
    private int cancelAmt;
    private int balanceAmt;
    private String cancelledAt;
    private String approveNo;
    private String ediDate;
    private String signature;
}
