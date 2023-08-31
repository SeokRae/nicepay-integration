package kr.co.nicepay.untact.terms.dto.term;

import kr.co.nicepay.untact.common.types.TermsType;
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
public class TermsReq {

    private TermsType termsType;

    private String returnCharSet;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        for (Field field : getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                if (value != null) {
                    if (value instanceof TermsType) {
                        map.put(field.getName(), ((TermsType) value).getCode());
                    } else {
                        map.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
