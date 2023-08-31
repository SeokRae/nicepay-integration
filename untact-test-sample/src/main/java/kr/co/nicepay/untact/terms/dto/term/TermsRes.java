package kr.co.nicepay.untact.terms.dto.term;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TermsRes {
    private String resultCode;
    private String resultMsg;
    private String termsTitle;
    private String content;
}
