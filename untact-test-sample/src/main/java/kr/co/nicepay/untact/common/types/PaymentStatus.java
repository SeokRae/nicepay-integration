package kr.co.nicepay.untact.common.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Slf4j
@Getter
@ToString
@RequiredArgsConstructor
public enum PaymentStatus {
    READY("ready"),
    PAID("paid"),
    PARTIAL_CANCELLED("partialCancelled"),
    CANCELLED("cancelled"),
    REFUNDED("refunded"),
    FAILED("failed"),
    EXPIRED("expired"),
    // receipt
    CANCEL_REQUESTED("cancelRequested"),
    ISSUE_REQUESTED("issueRequested"),
    EMPTY("");

    private final String status;

    @JsonCreator
    public static PaymentStatus of(String value) {
        log.info("{}", value);
        return Arrays.stream(values())
                .filter(method -> method.getStatus().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status: " + value));
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
