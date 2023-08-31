package kr.co.nicepay.untact.terms.dto.interestfree;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInterestFeeReq {
    @NotNull
    private boolean useAuth;
    @NotNull
    private String ediDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String signData;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String returnCharSet;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                map.put(field.getName(), field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
