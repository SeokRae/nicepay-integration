package kr.co.nicepay.untact.terms.dto.interestfree;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InterestFree {
    private String cardCode;
    private String cardName;
    // 무이자 할부 정보
    private List<FreeInstallment> freeInstallment;
}
