package kr.co.nicepay.untact.common.types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TermsType {
    ELECTRONIC_FINANCIAL_TRANSACTIONS("ElectronicFinancialTransactions", "전자금융거래 약관"),
    COLLECT_PERSONAL_INFO("CollectPersonalInfo", "개인정보 수집 및 이용 약관"),
    SHARING_PERSONAL_INFORMATION("SharingPersonalInformation", "개인정보 제 3자 제공약관"),
    TELECOMMUNICATION_CHARGING("TelecommunicationCharging", "통신과금서비스 이용약관");

    private final String code;
    private final String title;

}
