package kr.co.nicepay.untact.subscribe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.nicepay.untact.common.domain.Id;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscribeExpireRes {
    private String resultCode;
    private String resultMsg;
    private Id<String> tid;
    private Id<String> orderId;
    private Id<String> bid;
    private String authDate;
}
