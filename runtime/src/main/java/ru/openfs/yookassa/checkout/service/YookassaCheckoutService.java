package ru.openfs.yookassa.checkout.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import ru.openfs.yookassa.checkout.client.YookassaClient;
import ru.openfs.yookassa.checkout.client.model.Confirmation;
import ru.openfs.yookassa.checkout.client.model.PaymentRequest;
import ru.openfs.yookassa.checkout.config.YookassaCheckoutConfig;
import ru.openfs.yookassa.checkout.service.model.YookassaCheckoutRequest;
import ru.openfs.yookassa.checkout.service.model.YookassaCheckoutResponse;
import ru.openfs.yookassa.checkout.service.model.YookassaResponse;

import java.util.Optional;

@ApplicationScoped
public class YookassaCheckoutService {

    @Inject
    YookassaCheckoutConfig config;

    @Inject
    @RestClient
    YookassaClient client;

    public YookassaResponse<YookassaCheckoutResponse> checkout(YookassaCheckoutRequest request) {
        if (!config.enabled()) return YookassaResponse.error("yookassa client not enabled");

        return switch (request.type()) {
            case SBP -> createPayment(
                    PaymentRequest.createSbpPaymentRequest(
                            request.amount(), request.returnUrl(), request.description(), request.orderId()
                    ));

            case BANK_CARD -> createPayment(
                    PaymentRequest.createBankCardPaymentRequest(
                            request.amount(), request.returnUrl(), request.description(), request.orderId()
                    ));
        };
    }

    public YookassaResponse<String> getStatus(String paymentId) {
        if (!config.enabled()) return YookassaResponse.error("yookassa client not enabled");
        try {
            var response = client.getPayment(paymentId);
            return YookassaResponse.success(response.status());
        } catch (Exception e) {
            return YookassaResponse.error(e.getMessage());
        }
    }

    private YookassaResponse<YookassaCheckoutResponse> createPayment(PaymentRequest paymentRequest) {
        try {
            var response = client.payment(paymentRequest);
            String paymentId = Optional.ofNullable(response.id())
                    .orElseThrow(() -> new RuntimeException("no payment id"));
            String confirmUrl = Optional.ofNullable(response.confirmation())
                    .map(Confirmation::confirmationUrl)
                    .orElseThrow(() -> new RuntimeException("no payment confirmation"));

            return YookassaResponse.success(new YookassaCheckoutResponse(paymentId, confirmUrl));
        } catch (RuntimeException re) {
            return YookassaResponse.error(re.getMessage());
        }
    }
}
