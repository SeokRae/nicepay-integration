package kr.co.nicepay.untact.receipt.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReceiptRetrieveRequest {
    private String ediDate;
    private String signData;
    private String returnCharSet;
}
