package kr.co.nicepay.untact.payments.dto;

import kr.co.nicepay.untact.common.domain.Id;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsNetCancelReq {
    private Id<String> orderId;
    private String mallReserved;
    private String ediDate;
    private String signData;
    private String returnCharSet;
}
