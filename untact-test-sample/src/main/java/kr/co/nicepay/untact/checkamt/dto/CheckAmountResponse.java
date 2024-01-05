package kr.co.nicepay.untact.checkamt.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.nicepay.untact.common.domain.Id;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CheckAmountResponse {
    private String resultCode;
    private String resultMsg;
    private String ediDate;
    private String signature;
    //	 json data에서 isValid라는 이름으로 오는 값을 isValid라는 이름으로 바로 매핑이 안되서 @JsonProperty를 사용해서 매핑
    @JsonProperty("isValid")
    private boolean isValid;
    private Id<String> tid;
}