package kr.co.nicepay.untact.core.props.hosts;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties("domain")
public class DomainUrlProperties {

    private final String api;

    @ConstructorBinding
    public DomainUrlProperties(String api) {
        this.api = api;
        log.info("{}", this);
    }
}
