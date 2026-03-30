package ru.openfs.yookassa.checkout.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Confirmation(
        String type,
        @JsonProperty("return_url") String returnUrl,
        @JsonProperty("confirmation_url") String confirmationUrl
) {
    public Confirmation(String returnUrl) {
        this("redirect", returnUrl, null);
    }
}
