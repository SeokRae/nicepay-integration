package kr.co.nicepay.untact.core;


import kr.co.nicepay.untact.core.props.merchant.MerchantProperties;
import kr.co.nicepay.untact.subscribe.domain.CardEncData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardInfTest {

    @Autowired
    protected MerchantProperties merchantProperties;

    public String creditCard() {
        return CardEncData.builder()
                .cardNo("1234567890987654")
                .expYear("200")
                .expMonth("00")
                .idNo("12345678")
                .cardPw("00")
                .merchantSecretKey(merchantProperties.getSecretKey()) // 토큰 변경 시 수정 포인트
                .build().getEncData();
    }
}
