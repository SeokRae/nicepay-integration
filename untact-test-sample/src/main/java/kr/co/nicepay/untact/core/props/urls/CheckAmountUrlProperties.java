package kr.co.nicepay.untact.core.props.urls;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "uris.checks")
public class CheckAmountUrlProperties {

    private final String checkAmount;

    @ConstructorBinding
    public CheckAmountUrlProperties(String checkAmount) {
        this.checkAmount = checkAmount;
        log.info("{}", this);
    }
}
