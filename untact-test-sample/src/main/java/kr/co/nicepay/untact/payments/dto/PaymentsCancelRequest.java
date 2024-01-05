package kr.co.nicepay.untact.payments.dto;


import kr.co.nicepay.untact.common.domain.Id;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsCancelRequest {
    // Mandatory
    private String reason;
    private Id<String> orderId;
    private BigDecimal cancelAmt;
    private String mallReserved;
    private String ediDate;
    private String signData;
    private String returnCharSet;
    // Optional
    private BigDecimal taxFreeAmt;
    // Optional - refund
    private String refundAccount;
    private String refundBankCode;
    private String refundHolder;
}
