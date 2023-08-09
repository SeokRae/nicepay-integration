package kr.co.nicepay.untact.core.props.urls;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "uris.terms")
public class TermsUrlProperties {

    private final String terms;
    private final String cardEvent;
    private final String cardInterestFree;

    @ConstructorBinding
    public TermsUrlProperties(String terms, String cardEvent, String cardInterestFree) {
        this.terms = terms;
        this.cardEvent = cardEvent;
        this.cardInterestFree = cardInterestFree;
    }
}
