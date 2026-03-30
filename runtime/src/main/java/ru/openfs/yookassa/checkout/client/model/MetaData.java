package ru.openfs.yookassa.checkout.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MetaData(@JsonProperty("payment_id") String paymentId) {
}
