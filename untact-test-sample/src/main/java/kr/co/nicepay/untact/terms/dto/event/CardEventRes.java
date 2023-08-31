package kr.co.nicepay.untact.terms.dto.event;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardEventRes {
    private String resultCode;
    private String resultMsg;
    private String ediDate;
    private String signature;
    private String cardPoint;
    private List<CardEventInterestFree> interestFree;
}
