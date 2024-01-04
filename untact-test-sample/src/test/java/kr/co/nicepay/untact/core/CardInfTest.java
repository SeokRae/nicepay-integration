package kr.co.nicepay.untact.core;


import kr.co.nicepay.untact.core.props.merchant.MerchantProperties;
import kr.co.nicepay.untact.subscribe.domain.EncData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardInfTest {

    @Autowired
    protected MerchantProperties merchantProperties;

    public EncData creditCard() {
        return EncData.builder()
                .cardNo("1234567890987654")
                .expYear("200")
                .expMonth("00")
                .idNo("12345678")
                .cardPw("00")
                .build();
    }
}
