package kr.co.nicepay.untact.core.props.urls;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "uris.subscribes")
public class SubscribeUrlProperties {

    private final String issue;
    private final String payments;
    private final String expire;

    @ConstructorBinding
    public SubscribeUrlProperties(String issue, String payments, String expire) {
        this.issue = issue;
        this.payments = payments;
        this.expire = expire;
        log.info("{}", this);
    }
}
