package kr.co.nicepay.untact.terms.dto.interestfree;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FreeInstallment {
    private long minAmt;
    private long maxAmt;
    // 무이자 할부 개월
    private String installment;
    private String eventToDate;
}
