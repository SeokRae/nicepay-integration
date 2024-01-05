package kr.co.nicepay.untact.terms.dto.interestfree;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardInterestFreeResponse {

    private String resultCode;
    private String resultMsg;
    private String ediDate;
    private String signature;
    private List<InterestFree> interestFree;

}
