package kr.co.nicepay.untact.core.props.urls;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "uris")
public class AccessTokenUrlProperties {

    private final String issue;

    @ConstructorBinding
    public AccessTokenUrlProperties(String accessToken) {
        this.issue = accessToken;
        log.info("{}", this);
    }
}
