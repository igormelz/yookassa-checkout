package ru.openfs.yookassa.checkout.client.model;

public record Amount(
        String value,
        String currency
) {
    public Amount(String value) {
        this(value, "RUB");
    }
}
