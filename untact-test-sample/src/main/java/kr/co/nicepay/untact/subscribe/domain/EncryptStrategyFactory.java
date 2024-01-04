package kr.co.nicepay.untact.subscribe.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static kr.co.nicepay.untact.subscribe.domain.EncMode.A1;
import static kr.co.nicepay.untact.subscribe.domain.EncMode.A2;

@Slf4j
public enum EncryptStrategyFactory {
    AES128(A1, new AES128EncryptStrategy()),
    AES256(A2, new AES256EncryptStrategy());

    private final EncMode encMode;
    private final EncryptPolicy encryptPolicy;

    EncryptStrategyFactory(EncMode encMode, EncryptPolicy encryptPolicy) {
        this.encMode = encMode;
        this.encryptPolicy = encryptPolicy;
    }

    public static EncryptPolicy of(EncMode encMode) {
        log.info("encMode: {}", encMode);
        return Arrays.stream(values())
                .filter(policy -> policy.encMode.equals(encMode))
                .map(encryptStrategyFactory -> encryptStrategyFactory.encryptPolicy)
                .findFirst()
                .orElse(AES128.encryptPolicy); // Use AES128 as the default
    }
}
