package kr.co.nicepay.untact.common.types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
@RequiredArgsConstructor
public enum ReceiptType {

    UN_PUBLISHED("unPublished", "미발행", StringUtils.EMPTY),
    INDIVIDUAL("individual", "개인 소득공제용", "휴대폰번호"),
    COMPANY("company", "사업자 지출증빙용", "사업자번호");

    private final String title;
    private final String type;
    private final String inputDesc;
}
