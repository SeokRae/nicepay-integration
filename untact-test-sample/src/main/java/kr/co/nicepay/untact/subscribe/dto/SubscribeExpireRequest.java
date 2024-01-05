package kr.co.nicepay.untact.subscribe.dto;


import kr.co.nicepay.untact.common.domain.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeExpireRequest {
    private Id<String> orderId;
    private LocalDateTime ediDate;
    private String signData;
    private String returnCharSet;

}
