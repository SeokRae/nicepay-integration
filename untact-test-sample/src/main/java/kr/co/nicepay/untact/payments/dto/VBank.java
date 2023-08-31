package kr.co.nicepay.untact.payments.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VBank {
    private String vbankCode;
    private String vbankName;
    private String vbankNumber;
    private String vbankExpDate;
    private String vbankHolder;
}
