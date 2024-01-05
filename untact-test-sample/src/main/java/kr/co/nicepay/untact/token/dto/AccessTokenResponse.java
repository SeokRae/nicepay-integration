package kr.co.nicepay.untact.token.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@Builder
@ToString
@NoArgsConstructor
public class AccessTokenResponse {
    private String resultCode;
    private String resultMsg;
    private String accessToken;
    private String tokenType;
    private String expiredAt;
    private String now;

    @ConstructorProperties({"resultCode", "resultMsg", "accessToken", "tokenType", "expireAt", "now"})
    public AccessTokenResponse(String resultCode, String resultMsg, String accessToken, String tokenType, String expiredAt, String now) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiredAt = expiredAt;
        this.now = now;
    }
}
