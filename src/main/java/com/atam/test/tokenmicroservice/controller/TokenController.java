package com.atam.test.tokenmicroservice.controller;

import com.atam.test.tokenmicroservice.controller.mapper.TokenResponseMapper;
import com.atam.test.tokenmicroservice.controller.response.TokenResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TokenController {

  @Value("${token.username}")
  private String username;
  @Value("${token.password}")
  private String password;
  private final WebClient webClient;
  private final TokenResponseMapper mapper;

  @GetMapping("/get-token")
  public Mono<TokenResponse> getToken() {

    String requestBody = "{\n" +
        "    \"username\": \"" + username + "\",\n" +
        "    \"password\": \"" + password + "\"\n" +
        "}";
    Mono<ResponseEntity<String>> responseEntityMono = webClient.post()
        .body(BodyInserters.fromValue(requestBody)).retrieve().toEntity(String.class);

    return mapper.mapToTokenResponse(responseEntityMono);

  }
}
