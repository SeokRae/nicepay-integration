package kr.co.nicepay.untact.receipt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptCancelReq {
    // Mandatory
    private String orderId;
    private String reason;
    // Optional
    private BigDecimal cancelAmt; // 누락: 전체취소, 부분취소시 supplyAmt, goodsVat, taxFreeAmt, serviceAmt 필수
    private String ediDate;
    private String signData;
    private String returnCharSet;
    private BigDecimal supplyAmt;
    private BigDecimal goodsVat;
    private BigDecimal taxFreeAmt;
    private BigDecimal serviceAmt;
}
