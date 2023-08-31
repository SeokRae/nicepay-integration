package kr.co.nicepay.untact.payments.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    private String cardCode;
    private String cardName;
    private String cardNum;
    private int cardQuota;
    private Boolean isInterestFree;
    private String cardType;
    private Boolean canPartCancel;
    private String acquCardCode;
    private String acquCardName;
}
