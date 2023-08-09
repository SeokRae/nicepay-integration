package kr.co.nicepay.untact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = {"kr.co.nicepay.untact.core.props"})
public class UntactTestSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UntactTestSampleApplication.class, args);
    }

}
