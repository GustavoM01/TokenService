package com.atam.test.tokenmicroservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class ClientConfig {

  @Value("${token.base-url}")
  private String tokenBaseUrl;

  Logger logger = LoggerFactory.getLogger(ClientConfig.class);

  @Bean
  public WebClient webClient(WebClient.Builder builder) {
    logger.info("token base url is: " + tokenBaseUrl);
    return builder.baseUrl(tokenBaseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
  }

  ;
}
