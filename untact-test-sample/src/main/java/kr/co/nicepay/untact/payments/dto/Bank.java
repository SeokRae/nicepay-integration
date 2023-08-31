package kr.co.nicepay.untact.payments.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Bank {

    private final String bankCode;
    private final String bankName;

}
