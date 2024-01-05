package kr.co.nicepay.untact.payments.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsApproveRequest {

    private BigDecimal amount;
    private String ediDate;
    private String signData;
    private String returnCharSet;
}
