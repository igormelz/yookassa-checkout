package ru.openfs.yookassa.checkout.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentRequest(
        Amount amount,
        Boolean capture,
        @JsonProperty("payment_method_data") PaymentMethod paymentMethodData,
        Confirmation confirmation,
        String description,
        MetaData metadata
) {
    public PaymentRequest(
            String amount,
            String paymentMethod,
            String successUrl,
            String description,
            String orderId
    ) {
        this(
                new Amount(amount),
                true,
                new PaymentMethod(paymentMethod),
                new Confirmation(successUrl),
                description,
                new MetaData(orderId)
        );
    }

    public static PaymentRequest createBankCardPaymentRequest(Double amount, String returnUrl, String description, String orderId) {
        return new PaymentRequest(
                amount.toString(),
                "bank_card",
                returnUrl,
                description,
                orderId
        );
    }

    public static PaymentRequest createSbpPaymentRequest(Double amount, String returnUrl, String description, String orderId) {
        return new PaymentRequest(
                amount.toString(),
                "sbp",
                returnUrl,
                description,
                orderId
        );
    }
}

