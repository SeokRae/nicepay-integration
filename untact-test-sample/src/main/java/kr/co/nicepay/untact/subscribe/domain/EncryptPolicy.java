package kr.co.nicepay.untact.subscribe.domain;

@FunctionalInterface
public interface EncryptPolicy {
    String encrypt(String data, String secretKey);
}
