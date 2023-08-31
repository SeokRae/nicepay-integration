package kr.co.nicepay.untact.terms.dto.event;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardEventInterestFree {
    private String cardCode;
    private String cardName;
    // 무이자 할부 정보
    private String freeInstallment;
}
