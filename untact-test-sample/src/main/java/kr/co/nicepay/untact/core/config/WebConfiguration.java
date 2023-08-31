package kr.co.nicepay.untact.core.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import kr.co.nicepay.untact.core.interceptor.LoggingRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class WebConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // SerializationFeature.FAIL_ON_EMPTY_BEANS은 기본 getter가 없는 경우 실패를 방지하기 위해 사용
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES를 비활성화하면 JSON에 있는 알 수 없는 속성이 Java 객체에 매핑되지 않을 때 예외가 발생하지 않음
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // PropertyNamingStrategies.SNAKE_CASE를 설정하면 Java 필드 이름을 snake_case로 변환
        // objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        // setSerializationInclusion(JsonInclude.Include.NON_NULL)과 같은 설정을 통해 null 값이 있는 필드를 JSON 출력에서 제외
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 기타 필요한 설정 추가
        return objectMapper;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                // 5초 동안 응답이 없으면 예외 발생
                .setConnectTimeout(Duration.ofSeconds(5))
                // 5초 동안 데이터가 안오면 예외 발생
                .setReadTimeout(Duration.ofSeconds(5))
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter())
                .additionalInterceptors(new LoggingRequestInterceptor())
                .build();
    }
}
