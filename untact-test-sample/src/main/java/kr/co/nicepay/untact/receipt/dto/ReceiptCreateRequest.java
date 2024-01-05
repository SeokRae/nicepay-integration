package kr.co.nicepay.untact.receipt.dto;


import kr.co.nicepay.untact.common.types.ReceiptType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor
public class ReceiptCreateRequest {
    // Mandatory
    private String orderId;
    private BigDecimal amount;
    private String goodsName;
    private String receiptType;
    private String receiptNo;
    private int supplyAmt;
    private int goodsVat;
    private int taxFreeAmt;
    private int serviceAmt;
    // Optional
    private String buyerName;
    private String buyerTel;
    private String buyerEmail;
    private String signData;
    private String returnCharSet;

    @Builder
    public ReceiptCreateRequest(String orderId, BigDecimal amount, String goodsName, ReceiptType receiptType, String receiptNo, int supplyAmt, int goodsVat, int taxFreeAmt, int serviceAmt, String buyerName, String buyerTel, String buyerEmail, String signData, String returnCharSet) {
        this.orderId = orderId;
        this.amount = amount;
        this.goodsName = goodsName;
        this.receiptType = receiptType.getTitle();
        this.receiptNo = receiptNo;
        this.supplyAmt = supplyAmt;
        this.goodsVat = goodsVat;
        this.taxFreeAmt = taxFreeAmt;
        this.serviceAmt = serviceAmt;
        this.buyerName = buyerName;
        this.buyerTel = buyerTel;
        this.buyerEmail = buyerEmail;
        this.signData = signData;
        this.returnCharSet = returnCharSet;
    }
}
