package kr.co.nicepay.untact.terms.dto.interestfree;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import kr.co.nicepay.untact.common.utils.OptionalMultiValue;
import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
public class CardInterestFeeRequest {
    private boolean useAuth;
    private String ediDate;
    private String mid;
    private String signData;
    private String returnCharSet;

  public MultiValueMap<String, Object> toMultiValueMap() {
    MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

    OptionalMultiValue.addIfNotNull(map, "useAuth", useAuth);
    OptionalMultiValue.addIfNotNull(map, "ediDate", ediDate);
    OptionalMultiValue.addIfNotNull(map, "mid", mid);
    OptionalMultiValue.addIfNotNull(map, "signData", signData);
    OptionalMultiValue.addIfNotNull(map, "returnCharSet", returnCharSet);

    return map;
  }
}
