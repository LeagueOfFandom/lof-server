package com.lof.lofserver.config;

import com.lof.lofserver.service.certification.CertificationService;
import com.lof.lofserver.service.certification.google.GoogleCertificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "com.lof.lofserver")
public class LofConfig {
    @Bean
    @Primary
    public CertificationService certificationService() {
        return new GoogleCertificationService();
    }
}
