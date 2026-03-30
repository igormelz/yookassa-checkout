package ru.openfs.yookassa.checkout.service.model;

public record YookassaCheckoutRequest(
        YookassaCheckoutType type,
        String orderId,
        Double amount,
        String returnUrl,
        String description
) {
}
