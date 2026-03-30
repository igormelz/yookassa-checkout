package ru.openfs.yookassa.checkout.client;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import ru.openfs.yookassa.checkout.client.model.PaymentResponse;
import ru.openfs.yookassa.checkout.client.model.PaymentRequest;

@RegisterRestClient(baseUri = "https://api.yookassa.ru")
@RegisterClientHeaders(YookassaHeaderFactory.class)
@Path("/v3/payments")
public interface YookassaClient {
    @POST
    PaymentResponse payment(PaymentRequest request);
    @GET
    @Path("{payment_id}")
    PaymentResponse getPayment(@PathParam("payment_id") String paymentId);
}
