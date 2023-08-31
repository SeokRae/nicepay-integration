package kr.co.nicepay.untact.receipt.dto;


import kr.co.nicepay.untact.common.types.PaymentMethod;
import kr.co.nicepay.untact.common.types.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptRetrieveRes {
    private String resultCode;
    private String resultMsg;
    private String tid;

    private String orderId;
    private String ediDate;
    private String signature;
    private PaymentStatus status;
    private String approvedAt;
    private PaymentMethod payMethod;
    private BigDecimal amount;
    private BigDecimal balanceAmt;
    private String goodsName;
    private String approveNo;
    private String buyerName;
    private String buyerTel;
    private String buyerEmail;
    private String receiptUrl;
    private List<ReceiptCancel> receiptCancels;
}
