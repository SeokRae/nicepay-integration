package kr.co.nicepay.untact.receipt.dto;


import kr.co.nicepay.untact.common.domain.Id;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptCreateResponse {
    private String resultCode;
    private String resultMsg;
    private Id<String> tid;
    private Id<String> orderId;
    private String approvedAt;
    private String approveNo;
    private String ediDate;
    private String signature;
}
