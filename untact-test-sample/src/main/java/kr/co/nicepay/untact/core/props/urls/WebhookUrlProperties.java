package kr.co.nicepay.untact.core.props.urls;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Slf4j
@Getter
@ToString
@ConfigurationProperties(prefix = "uris.webhooks")
public class WebhookUrlProperties {

    private final String regist;
    private final String retrieve;
    private final String update;
    private final String delete;

    @ConstructorBinding
    public WebhookUrlProperties(String regist, String retrieve, String update, String delete) {
        this.regist = regist;
        this.retrieve = retrieve;
        this.update = update;
        this.delete = delete;
    }
}
