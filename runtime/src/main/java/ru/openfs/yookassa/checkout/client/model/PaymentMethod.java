package ru.openfs.yookassa.checkout.client.model;

public record PaymentMethod(
        String type,
        String id,
        Boolean saved
) {
    public PaymentMethod(String type) {
        this(type, null, null);
    }
}
