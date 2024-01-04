package kr.co.nicepay.untact.payments.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private BigDecimal couponAmt;
}
