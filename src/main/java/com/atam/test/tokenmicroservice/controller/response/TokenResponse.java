package com.atam.test.tokenmicroservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonPropertyOrder({"auth-vivelibre-token", "date"})
public class TokenResponse {

    @JsonProperty("auth-vivelibre-token")
    private String authViveLibreToken;
    private String date;
}
