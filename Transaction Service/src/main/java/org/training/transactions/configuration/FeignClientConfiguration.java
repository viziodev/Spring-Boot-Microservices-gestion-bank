package org.training.transactions.configuration;

import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration extends FeignClientProperties.FeignClientConfiguration {

    /**
     * Returns an instance of ErrorDecoder that will be used to decode errors returned by Feign clients.
     *
     * @return the ErrorDecoder instance
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientErrorDecoder();
    }
}
