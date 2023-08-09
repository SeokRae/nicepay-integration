package kr.co.nicepay.untact.core.props.urls;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "uris.receipts")
public class ReceiptUrlProperties {

    private final String issue;
    private final String retrieve;
    private final String cancel;

    @ConstructorBinding
    public ReceiptUrlProperties(String issue, String retrieve, String cancel) {
        this.issue = issue;
        this.retrieve = retrieve;
        this.cancel = cancel;
        log.info("{}", this);
    }
}
