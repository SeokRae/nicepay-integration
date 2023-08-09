package kr.co.nicepay.untact.core.props.urls;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "uris.checkout")
public class CheckoutUrlProperties {

    private final String issue;
    private final String retrieve;
    private final String expire;
    private final String cancel;
    private final String retrievePayments;

    @ConstructorBinding
    public CheckoutUrlProperties(String issue, String retrieve, String expire, String cancel, String retrievePayments) {
        this.issue = issue;
        this.retrieve = retrieve;
        this.expire = expire;
        this.cancel = cancel;
        this.retrievePayments = retrievePayments;
        log.info("{}", this);
    }
}
