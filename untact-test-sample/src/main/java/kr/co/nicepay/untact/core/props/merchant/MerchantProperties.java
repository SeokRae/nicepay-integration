package kr.co.nicepay.untact.core.props.merchant;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Base64;

@Slf4j
@Getter
@ToString
@ConfigurationProperties("merchant")
public class MerchantProperties {

    private final String clientId;
    private final String secretKey;
    private final String token;
    private final String returnUrl;
    private final String webhookUrl;
    private final String managerEmail;

    @ConstructorBinding
    public MerchantProperties(String clientId, String secretKey, String returnUrl, String webhookUrl, String managerEmail) {
        this.clientId = clientId;
        this.secretKey = secretKey;
        this.returnUrl = returnUrl;
        this.webhookUrl = webhookUrl;
        this.managerEmail = managerEmail;
        // Basic Token 생성
        this.token = encodeToken(clientId, secretKey);
        log.info("{}", this);
    }

    private String encodeToken(String clientId, String secretKey) {
        return Base64.getEncoder().encodeToString((clientId + ":" + secretKey).getBytes());
    }
}
