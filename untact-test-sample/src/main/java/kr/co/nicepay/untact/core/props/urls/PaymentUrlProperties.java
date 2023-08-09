package kr.co.nicepay.untact.core.props.urls;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "uris.payments")
public class PaymentUrlProperties {

    private final String retrieveTid;
    private final String retrieveOrderId;
    private final String approve;
    private final String cancel;
    private final String netCancel;

    @ConstructorBinding
    public PaymentUrlProperties(String retrieveTid, String retrieveOrderId, String approve, String cancel, String netCancel) {
        this.retrieveTid = retrieveTid;
        this.retrieveOrderId = retrieveOrderId;
        this.approve = approve;
        this.cancel = cancel;
        this.netCancel = netCancel;
        log.info("{}", this);
    }
}
