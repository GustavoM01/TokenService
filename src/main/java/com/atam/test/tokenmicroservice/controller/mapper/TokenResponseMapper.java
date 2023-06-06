package com.atam.test.tokenmicroservice.controller.mapper;

import com.atam.test.tokenmicroservice.controller.response.TokenResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class TokenResponseMapper {

  private final ObjectMapper objectMapper;
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMMM d, yyyy");

  public Mono<TokenResponse> mapToTokenResponse(Mono<ResponseEntity<String>> responseMono) {
    return responseMono.flatMap(responseEntity -> {
      try {
        JsonNode node = objectMapper.readTree(responseEntity.getBody());
        TokenResponse response = TokenResponse.builder()
            .authViveLibreToken(node.get("token").asText())
            .date(LocalDate.now(ZoneId.of("Europe/Madrid")).format(FORMATTER)).build();
        return Mono.just(response);
      } catch (IOException e) {
        return Mono.error(e);
      }
    });
  }
}
