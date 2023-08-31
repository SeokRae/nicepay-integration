package kr.co.nicepay.untact.checkamt.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckAmountReq {
    private BigDecimal amount;
    private String ediDate;
    private String signData;
    private String returnCharSet;
}
